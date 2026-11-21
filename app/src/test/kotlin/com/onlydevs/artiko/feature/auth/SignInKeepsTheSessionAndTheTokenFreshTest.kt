package com.onlydevs.artiko.feature.auth

import com.onlydevs.artiko.data.auth.AuthException
import com.onlydevs.artiko.data.auth.AuthFailure
import com.onlydevs.artiko.data.auth.AuthRepository
import com.onlydevs.artiko.data.auth.SignedInUser
import com.onlydevs.artiko.data.auth.jwtExpiresAt
import com.onlydevs.artiko.support.FakeAuthApi
import com.onlydevs.artiko.support.MemorySessionStore
import com.onlydevs.artiko.support.fakeJwt
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Test

class SignInKeepsTheSessionAndTheTokenFreshTest {
    private var now = 1_000L
    private val api = FakeAuthApi()
    private val store = MemorySessionStore()
    private val repository = AuthRepository(api, store, nowSeconds = { now })

    @Test
    fun signingInSavesTheSessionAndGivesTheAccount() = runTest {
        repository.signIn("rado@example.test", "password-1")

        assertEquals(SignedInUser("Rado", "rado@example.test"), repository.user.value)
        assertEquals("session-1", store.session?.sessionToken)
        assertEquals(fakeJwt(10_000), repository.accessToken())
    }

    @Test
    fun theTokenIsReusedUntilItIsAboutToExpire() = runTest {
        repository.signIn("rado@example.test", "password-1")
        val calls = api.tokenCalls

        now = 9_000
        repository.accessToken()
        assertEquals(calls, api.tokenCalls)

        now = 9_950 // less than a minute before it expires
        repository.accessToken()
        assertEquals(calls + 1, api.tokenCalls)
    }

    @Test
    fun aRefusedSessionSignsTheVisitorOut() = runTest {
        repository.signIn("rado@example.test", "password-1")
        api.tokenStatus = 401

        assertNull(repository.refreshToken())

        assertNull(store.session)
        assertNull(repository.user.value)
    }

    @Test
    fun noConnectionKeepsTheSession() = runTest {
        repository.signIn("rado@example.test", "password-1")
        api.offline = true

        assertNull(repository.refreshToken())

        assertEquals("session-1", store.session?.sessionToken)
    }

    @Test
    fun wrongPasswordsAndOfflineStartsAreExplained() = runTest {
        api.signInStatus = 401
        assertEquals(AuthFailure.InvalidCredentials, failureOf { repository.signIn("a@b.c", "wrong") })

        api.signInStatus = 422
        api.signInErrorCode = "USER_ALREADY_EXISTS"
        assertEquals(AuthFailure.AlreadyRegistered, failureOf { repository.signUp("A", "a@b.c", "password-1") })

        api.offline = true
        assertEquals(AuthFailure.Offline, failureOf { repository.signIn("a@b.c", "password-1") })
        assertNull(store.session)
    }

    @Test
    fun signingOutForgetsTheSessionEvenWithoutConnection() = runTest {
        repository.signIn("rado@example.test", "password-1")
        api.offline = true

        repository.signOut()

        assertNull(store.session)
        assertNull(repository.user.value)
    }

    @Test
    fun readsTheExpiryOfAToken() {
        assertEquals(1234L, jwtExpiresAt(fakeJwt(1234)))
        assertNull(jwtExpiresAt("not-a-token"))
    }

    private suspend fun failureOf(action: suspend () -> Unit): AuthFailure =
        assertThrows(AuthException::class.java) { runBlocking { action() } }.failure
}
