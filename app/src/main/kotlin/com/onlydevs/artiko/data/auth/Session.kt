package com.onlydevs.artiko.data.auth

import kotlinx.serialization.Serializable

/** What the phone keeps after signing in: the long-lived session token and the current API token. */
@Serializable
data class Session(
    val sessionToken: String,
    val name: String,
    val email: String,
    val jwt: String? = null,
    val jwtExpiresAt: Long = 0,
)

interface SessionStore {
    suspend fun read(): Session?

    suspend fun save(session: Session)

    suspend fun clear()
}
