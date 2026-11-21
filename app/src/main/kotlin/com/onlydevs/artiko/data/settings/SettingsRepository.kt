package com.onlydevs.artiko.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

private val THEME = stringPreferencesKey("theme")
private val TEXT_SIZE = stringPreferencesKey("text_size")
private val REDUCE_MOTION = booleanPreferencesKey("reduce_motion")
private val FLAT_MODE = booleanPreferencesKey("flat_mode")
private val HAPTICS = booleanPreferencesKey("haptics")
private val DATA_SAVER = booleanPreferencesKey("data_saver")

/** The visitor's choices on the Settings pages, kept on the phone. */
class SettingsRepository(private val dataStore: DataStore<Preferences>) {
    val settings: Flow<AppSettings> = dataStore.data.map(::readFrom)

    suspend fun update(change: (AppSettings) -> AppSettings) {
        dataStore.edit { prefs ->
            val current = change(readFrom(prefs))
            prefs[THEME] = current.theme.name
            prefs[TEXT_SIZE] = current.textSize.name
            prefs[REDUCE_MOTION] = current.reduceMotion
            prefs[FLAT_MODE] = current.flatMode
            prefs[HAPTICS] = current.haptics
            prefs[DATA_SAVER] = current.dataSaver
        }
    }

    private fun readFrom(prefs: Preferences): AppSettings {
        val defaults = AppSettings()
        return AppSettings(
            theme = prefs[THEME]?.let { runCatching { ThemeMode.valueOf(it) }.getOrNull() } ?: defaults.theme,
            textSize = prefs[TEXT_SIZE]?.let { runCatching { TextSize.valueOf(it) }.getOrNull() } ?: defaults.textSize,
            reduceMotion = prefs[REDUCE_MOTION] ?: defaults.reduceMotion,
            flatMode = prefs[FLAT_MODE] ?: defaults.flatMode,
            haptics = prefs[HAPTICS] ?: defaults.haptics,
            dataSaver = prefs[DATA_SAVER] ?: defaults.dataSaver,
        )
    }
}
