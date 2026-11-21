package com.onlydevs.artiko.data.auth

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/** Sends the API token with every request while the visitor is signed in. */
class AuthInterceptor(private val repository: AuthRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { repository.accessToken() } ?: return chain.proceed(chain.request())
        return chain.proceed(chain.request().withToken(token))
    }
}

/** When the API answers 401, gets a new token once and repeats the request with it. */
class TokenAuthenticator(private val repository: AuthRepository) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.priorResponse != null) return null
        val token = runBlocking { repository.refreshToken() } ?: return null
        return response.request.withToken(token)
    }
}

private fun Request.withToken(token: String): Request = newBuilder().header("Authorization", "Bearer $token").build()
