package com.onlydevs.artiko.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.onlydevs.artiko.data.settings.ThemeMode

@Composable
fun ArtikoTheme(content: @Composable () -> Unit) {
    val settings = LocalAppSettings.current
    val dark = when (settings.theme) {
        ThemeMode.Dark -> true
        ThemeMode.Light -> false
        ThemeMode.System -> isSystemInDarkTheme()
    }
    val density = LocalDensity.current
    // The text size setting scales every sp of the app.
    val scaled = Density(density.density, density.fontScale * settings.textSize.scale)
    CompositionLocalProvider(LocalDensity provides scaled) {
        MaterialTheme(
            colorScheme = if (dark) artikoDarkColorScheme else artikoColorScheme,
            typography = artikoTypography,
            shapes = artikoShapes,
            content = content,
        )
    }
}
