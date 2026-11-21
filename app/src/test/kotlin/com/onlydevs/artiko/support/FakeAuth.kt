package com.onlydevs.artiko.support

import com.onlydevs.artiko.data.auth.AuthApi
import com.onlydevs.artiko.data.auth.AuthResponse
import com.onlydevs.artiko.data.auth.AuthUser
import com.onlydevs.artiko.data.auth.Session
import com.onlydevs.artiko.data.auth.SessionStore
import com.onlydevs.artiko.data.auth.SignInBody
import com.onlydevs.artiko.data.auth.SignUpBody
import com.onlydevs.artiko.data.auth.TokenResponse
import java.io.IOException
import java.util.Base64
import okhttp3.Headers.Companion.headersOf
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

/** A session store that lives in memory. */
class MemorySessionStore(var session: Session? = null) : SessionStore {
    override suspend fun read(): Session? = session

    override suspend fun save(session: Session) {
        this.session = session
    }

    override suspend fun clear() {
        session = null
    }
}

/** A JWT-shaped string that expires at [expiresAt] (Unix seconds); the signature is not checked by the app. */
fun fakeJwt(expiresAt: Long): String {
    val encode = { text: String -> Base64.getUrlEncoder().withoutPadding().encodeToString(text.toByteArray()) }
    return "${encode("""{"alg":"none"}""")}.${encode("""{"exp":$expiresAt}""")}.signature"
}

class FakeAuthApi : AuthApi {
    var offline = false
    var signInStatus = 200
    var signInErrorCode = "INVALID_EMAIL_OR_PASSWORD"
    var sessionToken: String? = "session-1"
    var tokenStatus = 200
    var jwtExpiresAt = 10_000L
    var tokenCalls = 0
    var signOutCalls = 0

    private fun failure(): Response<AuthResponse> = Response.error(
        signInStatus,
        """{"message":"no","code":"$signInErrorCode"}""".toResponseBody("application/json".toMediaType()),
    )

    private suspend fun answer(user: AuthUser): Response<AuthResponse> {
        if (offline) throw IOException("offline")
        if (signInStatus != 200) return failure()
        val headers = sessionToken?.let { headersOf("set-auth-token", it) } ?: headersOf()
        return Response.success(AuthResponse(token = sessionToken, user = user), headers)
    }

    override suspend fun signIn(body: SignInBody) = answer(AuthUser("u1", body.email, "Rado"))

    override suspend fun signUp(body: SignUpBody) = answer(AuthUser("u1", body.email, body.name))

    override suspend fun token(authorization: String): TokenResponse {
        tokenCalls++
        if (offline) throw IOException("offline")
        if (tokenStatus != 200) {
            val body = "".toResponseBody("text/plain".toMediaType())
            throw HttpException(Response.error<TokenResponse>(tokenStatus, body))
        }
        return TokenResponse(fakeJwt(jwtExpiresAt))
    }

    override suspend fun signOut(authorization: String): Response<Unit> {
        signOutCalls++
        if (offline) throw IOException("offline")
        return Response.success(Unit)
    }
}
