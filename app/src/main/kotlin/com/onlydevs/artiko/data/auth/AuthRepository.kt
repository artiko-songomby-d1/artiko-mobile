package com.onlydevs.artiko.data.auth

import com.onlydevs.artiko.data.artikoJson
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.HttpException
import retrofit2.Response

private const val SESSION_HEADER = "set-auth-token"
private const val EXPIRY_MARGIN_SECONDS = 60L
private const val FALLBACK_LIFETIME_SECONDS = 600L
private const val UNAUTHORIZED = 401

enum class AuthFailure { Offline, InvalidCredentials, AlreadyRegistered, WeakPassword, Rejected, Unavailable }

class AuthException(val failure: AuthFailure) : Exception(failure.name)

/** Signs in through Better Auth, keeps the session safe, and hands out a fresh API token on demand. */
class AuthRepository(
    private val api: AuthApi,
    private val store: SessionStore,
    private val nowSeconds: () -> Long = { System.currentTimeMillis() / 1000 },
) {
    private val lock = Mutex()
    private val mutableUser = MutableStateFlow<SignedInUser?>(null)

    /** The signed-in account, or null. */
    val user: StateFlow<SignedInUser?> = mutableUser.asStateFlow()

    /** Loads the account of the saved session, if any; call once when the app starts. */
    suspend fun restore() {
        mutableUser.value = store.read()?.let { SignedInUser(it.name, it.email) }
    }

    suspend fun signIn(email: String, password: String) = authenticate { api.signIn(SignInBody(email, password)) }

    suspend fun signUp(name: String, email: String, password: String) =
        authenticate { api.signUp(SignUpBody(name, email, password)) }

    private suspend fun authenticate(call: suspend () -> Response<AuthResponse>) {
        val response = try {
            call()
        } catch (offline: IOException) {
            throw AuthException(AuthFailure.Offline)
        }
        if (!response.isSuccessful) throw AuthException(failureOf(response))
        val sessionToken = response.headers()[SESSION_HEADER] ?: throw AuthException(AuthFailure.Unavailable)
        val user = response.body()?.user
        val email = user?.email.orEmpty()
        val session = Session(sessionToken, user?.name?.ifBlank { null } ?: email, email)
        store.save(session)
        if (lock.withLock { refresh(session) } == null) {
            store.clear()
            throw AuthException(AuthFailure.Unavailable)
        }
        mutableUser.value = SignedInUser(session.name, session.email)
    }

    /** A valid API token, refreshed shortly before it expires; null when signed out. */
    suspend fun accessToken(): String? = lock.withLock {
        val session = store.read() ?: return null
        val jwt = session.jwt
        if (jwt != null && session.jwtExpiresAt - EXPIRY_MARGIN_SECONDS > nowSeconds()) jwt else refresh(session)
    }

    /** A new API token even if the current one looks valid; used after the API refused it. */
    suspend fun refreshToken(): String? = lock.withLock { store.read()?.let { refresh(it) } }

    suspend fun signOut() {
        store.read()?.let { runCatching { api.signOut("Bearer ${it.sessionToken}") } }
        store.clear()
        mutableUser.value = null
    }

    private suspend fun refresh(session: Session): String? = try {
        val token = api.token("Bearer ${session.sessionToken}").token
        val expiresAt = jwtExpiresAt(token) ?: (nowSeconds() + FALLBACK_LIFETIME_SECONDS)
        store.save(session.copy(jwt = token, jwtExpiresAt = expiresAt))
        token
    } catch (refused: HttpException) {
        // The session itself is gone (expired or revoked): the visitor has to sign in again.
        if (refused.code() == UNAUTHORIZED) {
            store.clear()
            mutableUser.value = null
        }
        null
    } catch (offline: IOException) {
        null
    }

    private fun failureOf(response: Response<AuthResponse>): AuthFailure {
        val code = runCatching {
            artikoJson.decodeFromString<AuthError>(response.errorBody()?.string().orEmpty()).code
        }.getOrNull()
        return when {
            response.code() >= SERVER_ERROR -> AuthFailure.Unavailable
            code == "INVALID_EMAIL_OR_PASSWORD" || response.code() == UNAUTHORIZED -> AuthFailure.InvalidCredentials
            code?.startsWith("USER_ALREADY_EXISTS") == true -> AuthFailure.AlreadyRegistered
            code?.startsWith("PASSWORD_") == true -> AuthFailure.WeakPassword
            else -> AuthFailure.Rejected
        }
    }
}

private const val SERVER_ERROR = 500
