package com.onlydevs.artiko.data.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignInBody(val email: String, val password: String)

@Serializable
data class SignUpBody(val name: String, val email: String, val password: String)

@Serializable
data class AuthUser(val id: String? = null, val email: String? = null, val name: String? = null)

@Serializable
data class AuthResponse(val token: String? = null, val user: AuthUser? = null)

@Serializable
data class TokenResponse(val token: String)

@Serializable
data class AuthError(val message: String? = null, val code: String? = null)

/** The account shown in the app. */
data class SignedInUser(val name: String, val email: String)
