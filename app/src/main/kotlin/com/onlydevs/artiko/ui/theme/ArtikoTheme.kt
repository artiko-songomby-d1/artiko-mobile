package com.onlydevs.artiko.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ArtikoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = artikoColorScheme,
        typography = artikoTypography,
        shapes = artikoShapes,
        content = content,
    )
}
