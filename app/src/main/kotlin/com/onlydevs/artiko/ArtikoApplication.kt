package com.onlydevs.artiko

import android.app.Application
import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.data.auth.AuthInterceptor
import com.onlydevs.artiko.data.auth.AuthRepository
import com.onlydevs.artiko.data.auth.TinkSessionStore
import com.onlydevs.artiko.data.auth.TokenAuthenticator
import com.onlydevs.artiko.data.auth.createAuthApi
import com.onlydevs.artiko.data.auth.createSessionAead
import com.onlydevs.artiko.data.auth.sessionDataStore
import com.onlydevs.artiko.data.createArtikoApi
import com.onlydevs.artiko.data.settings.SettingsRepository
import com.onlydevs.artiko.data.settings.settingsDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class ArtikoApplication : Application() {
    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val authRepository: AuthRepository by lazy {
        val store = TinkSessionStore(sessionDataStore, createSessionAead(this))
        AuthRepository(createAuthApi(BuildConfig.AUTH_BASE_URL), store)
    }

    val settingsRepository: SettingsRepository by lazy { SettingsRepository(settingsDataStore) }

    val api: ArtikoApi by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(authRepository))
            .authenticator(TokenAuthenticator(authRepository))
            .build()
        createArtikoApi(BuildConfig.API_BASE_URL, client)
    }

    override fun onCreate() {
        super.onCreate()
        appScope.launch { authRepository.restore() }
    }
}
