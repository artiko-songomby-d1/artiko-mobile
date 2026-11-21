package com.onlydevs.artiko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.onlydevs.artiko.data.settings.AppSettings
import com.onlydevs.artiko.ui.theme.ArtikoTheme
import com.onlydevs.artiko.ui.theme.LocalAppSettings

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val settingsRepository = (application as ArtikoApplication).settingsRepository
        setContent {
            val settings by settingsRepository.settings.collectAsStateWithLifecycle(initialValue = AppSettings())
            CompositionLocalProvider(LocalAppSettings provides settings) {
                ArtikoTheme {
                    ArtikoApp()
                }
            }
        }
    }
}
