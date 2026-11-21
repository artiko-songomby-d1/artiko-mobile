package com.onlydevs.artiko.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Palette of artiko-web: warm gallery wall, cream paper, ink and one red accent.
internal val Ink = Color(0xFF141110)
internal val BodyText = Color(0xFF4A4542)
internal val Muted = Color(0xFF6B6461)
internal val Paper = Color(0xFFFFFFFF)
internal val Wall = Color(0xFFE8E6E1)
internal val Cream = Color(0xFFFFF4EC)
internal val Red = Color(0xFFE5574A)
internal val RedDeep = Color(0xFFC9443A)
private const val SOFT_LINE_ALPHA = 0.25f

val artikoColorScheme = lightColorScheme(
    primary = Ink,
    onPrimary = Paper,
    secondary = Red,
    onSecondary = Paper,
    tertiary = RedDeep,
    secondaryContainer = Cream,
    onSecondaryContainer = RedDeep,
    background = Wall,
    onBackground = Ink,
    surface = Wall,
    onSurface = Ink,
    onSurfaceVariant = Muted,
    surfaceContainer = Cream,
    surfaceContainerHighest = Cream,
    outline = Ink,
    outlineVariant = Ink.copy(alpha = SOFT_LINE_ALPHA),
)

private val NightWall = Color(0xFF1B1918)
private val NightPaper = Color(0xFF2A2725)
private val NightMuted = Color(0xFFB7B0AC)

val artikoDarkColorScheme = darkColorScheme(
    primary = Cream,
    onPrimary = Ink,
    secondary = Red,
    onSecondary = Paper,
    tertiary = Red,
    secondaryContainer = NightPaper,
    onSecondaryContainer = Cream,
    background = NightWall,
    onBackground = Cream,
    surface = NightWall,
    onSurface = Cream,
    onSurfaceVariant = NightMuted,
    surfaceContainer = NightPaper,
    surfaceContainerHighest = NightPaper,
    outline = Cream,
    outlineVariant = Cream.copy(alpha = SOFT_LINE_ALPHA),
)
