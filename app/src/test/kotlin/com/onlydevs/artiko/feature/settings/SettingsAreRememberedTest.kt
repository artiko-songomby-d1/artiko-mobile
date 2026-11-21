package com.onlydevs.artiko.feature.settings

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.onlydevs.artiko.data.settings.AppSettings
import com.onlydevs.artiko.data.settings.SettingsRepository
import com.onlydevs.artiko.data.settings.TextSize
import com.onlydevs.artiko.data.settings.ThemeMode
import java.io.File
import java.nio.file.Files
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SettingsAreRememberedTest {
    private val file = File(Files.createTempDirectory("settings").toFile(), "settings.preferences_pb")
    private val repository = SettingsRepository(
        PreferenceDataStoreFactory.create(scope = CoroutineScope(Dispatchers.IO)) { file },
    )

    @Test
    fun startsWithTheDefaults() = runBlocking {
        assertEquals(AppSettings(), repository.settings.first())
    }

    @Test
    fun keepsEveryChoice() = runBlocking {
        repository.update {
            it.copy(
                theme = ThemeMode.Dark,
                textSize = TextSize.Large,
                reduceMotion = true,
                flatMode = true,
                haptics = false,
                dataSaver = true,
            )
        }

        val saved = repository.settings.first()
        assertEquals(ThemeMode.Dark, saved.theme)
        assertEquals(TextSize.Large, saved.textSize)
        assertTrue(saved.reduceMotion && saved.flatMode && saved.dataSaver)
        assertFalse(saved.haptics)
    }

    @Test
    fun aChangeKeepsTheOtherChoices() = runBlocking {
        repository.update { it.copy(dataSaver = true) }
        repository.update { it.copy(theme = ThemeMode.Light) }

        val saved = repository.settings.first()
        assertTrue(saved.dataSaver)
        assertEquals(ThemeMode.Light, saved.theme)
    }

    @Test
    fun depthEffectsNeedMotionAndTheThreeDLook() {
        assertTrue(AppSettings().depthEffects)
        assertFalse(AppSettings(reduceMotion = true).depthEffects)
        assertFalse(AppSettings(flatMode = true).depthEffects)
    }
}
