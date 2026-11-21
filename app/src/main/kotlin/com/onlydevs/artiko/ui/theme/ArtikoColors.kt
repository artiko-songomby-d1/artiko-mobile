package com.onlydevs.artiko.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Palette of artiko-web: warm gallery wall, white paper, ink buttons and one red accent.
private val Ink = Color(0xFF111111)
private val Muted = Color(0xFF6B6B6B)
private val Paper = Color(0xFFFFFFFF)
private val Wall = Color(0xFFE8E6E1)
private val Red = Color(0xFFE5574A)
private val RedDeep = Color(0xFFB3382C)
private val RedTint = Color(0xFFF7D9D5)
private val Line = Color(0xFF555555)

val artikoColorScheme = lightColorScheme(
    primary = Ink,
    onPrimary = Paper,
    secondary = Red,
    onSecondary = Paper,
    secondaryContainer = RedTint,
    onSecondaryContainer = RedDeep,
    background = Wall,
    onBackground = Ink,
    surface = Wall,
    onSurface = Ink,
    onSurfaceVariant = Muted,
    surfaceContainer = Paper,
    surfaceContainerHighest = Paper,
    outline = Line,
    outlineVariant = Line,
)
