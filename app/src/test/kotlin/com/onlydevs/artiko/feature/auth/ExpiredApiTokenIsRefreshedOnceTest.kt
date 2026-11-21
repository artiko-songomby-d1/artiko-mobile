package com.onlydevs.artiko.feature.auth

import com.onlydevs.artiko.data.auth.AuthInterceptor
import com.onlydevs.artiko.data.auth.AuthRepository
import com.onlydevs.artiko.data.auth.TokenAuthenticator
import com.onlydevs.artiko.data.createArtikoApi
import com.onlydevs.artiko.support.FakeAuthApi
import com.onlydevs.artiko.support.MemorySessionStore
import com.onlydevs.artiko.support.fakeJwt
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ExpiredApiTokenIsRefreshedOnceTest {
    private val server = MockWebServer()
    private val auth = FakeAuthApi()
    private val repository = AuthRepository(auth, MemorySessionStore(), nowSeconds = { 1_000L })

    @After
    fun stopServer() = server.shutdown()

    private fun apiFor(vararg answers: MockResponse) = createArtikoApi(
        server.apply { answers.forEach(::enqueue) }.url("/").toString(),
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(repository))
            .authenticator(TokenAuthenticator(repository))
            .build(),
    )

    private val ok = MockResponse().setBody("[]").addHeader("Content-Type", "application/json")

    @Test
    fun theSignedInVisitorSendsTheirToken() = runBlocking {
        repository.signIn("rado@example.test", "password-1")

        apiFor(ok).listCategories()

        assertEquals("Bearer ${fakeJwt(10_001)}", server.takeRequest().getHeader("Authorization"))
    }

    @Test
    fun aRefusedTokenIsReplacedAndTheCallIsRepeatedOnce() = runBlocking {
        repository.signIn("rado@example.test", "password-1")

        apiFor(MockResponse().setResponseCode(401), ok).listCategories()

        assertEquals("Bearer ${fakeJwt(10_001)}", server.takeRequest().getHeader("Authorization"))
        assertEquals("Bearer ${fakeJwt(10_002)}", server.takeRequest().getHeader("Authorization"))
    }

    @Test
    fun aSignedOutVisitorSendsNoToken() = runBlocking {
        apiFor(ok).listCategories()

        assertNull(server.takeRequest().getHeader("Authorization"))
    }
}
