package com.onlydevs.artiko.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import com.onlydevs.artiko.data.settings.AppSettings

/** The visitor's Settings choices, available to every composable. */
val LocalAppSettings = staticCompositionLocalOf { AppSettings() }
