package com.onlydevs.artiko.feature.settings

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.onlydevs.artiko.data.auth.AuthFailure
import com.onlydevs.artiko.data.auth.AuthRepository
import com.onlydevs.artiko.data.settings.SettingsRepository
import com.onlydevs.artiko.data.settings.ThemeMode
import com.onlydevs.artiko.support.FakeAuthApi
import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.support.MemorySessionStore
import java.io.File
import java.nio.file.Files
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

class SettingsChoicesApplyAndAccountWorksTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    private val api = FakeAuthApi()
    private val repository = SettingsRepository(
        PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO)) {
            File(Files.createTempDirectory("settings").toFile(), "settings.preferences_pb")
        },
    )

    // Built on first use: the main dispatcher rule has to be active before a ViewModel starts.
    private val viewModel by lazy { SettingsViewModel(repository, AuthRepository(api, MemorySessionStore())) }

    @Test
    fun aChoiceIsSavedAndReachesTheWholeApp() = runBlocking {
        viewModel.change { it.copy(theme = ThemeMode.Dark, dataSaver = true) }

        val saved = withTimeout(5_000) { repository.settings.first { it.theme == ThemeMode.Dark } }
        assertEquals(true, saved.dataSaver)
    }

    @Test
    fun signingInShowsTheAccountAndSigningOutRemovesIt() = runBlocking {
        viewModel.signIn(" rado@example.test ", "password-1")
        assertNotNull(viewModel.user.value)
        assertFalse(viewModel.account.value.busy)

        viewModel.signOut()
        assertNull(viewModel.user.value)
    }

    @Test
    fun aWrongPasswordIsExplainedAndNobodyIsSignedIn() {
        api.signInStatus = 401

        viewModel.signIn("rado@example.test", "wrong")

        assertEquals(AuthFailure.InvalidCredentials, viewModel.account.value.failure)
        assertNull(viewModel.user.value)
    }
}
