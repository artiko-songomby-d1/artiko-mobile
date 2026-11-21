package com.onlydevs.artiko.data.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/** Better Auth, served by artiko-web: sign in there, then exchange the session for an API token. */
interface AuthApi {
    @POST("api/auth/sign-in/email")
    suspend fun signIn(@Body body: SignInBody): Response<AuthResponse>

    @POST("api/auth/sign-up/email")
    suspend fun signUp(@Body body: SignUpBody): Response<AuthResponse>

    /** The short-lived JWT the API accepts; [authorization] is `Bearer <session token>`. */
    @GET("api/auth/token")
    suspend fun token(@Header("Authorization") authorization: String): TokenResponse

    @POST("api/auth/sign-out")
    suspend fun signOut(@Header("Authorization") authorization: String): Response<Unit>
}
