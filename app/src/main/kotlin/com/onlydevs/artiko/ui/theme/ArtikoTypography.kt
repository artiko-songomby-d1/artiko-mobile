package com.onlydevs.artiko.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.onlydevs.artiko.R

private const val REGULAR_WEIGHT = 400
private const val MEDIUM_WEIGHT = 500

// Fonts of artiko-web: Inter for text, League Gothic for titles.
private val Inter = FontFamily(
    Font(
        resId = R.font.inter,
        weight = FontWeight.Normal,
        variationSettings = FontVariation.Settings(FontVariation.weight(REGULAR_WEIGHT)),
    ),
    Font(
        resId = R.font.inter,
        weight = FontWeight.Medium,
        variationSettings = FontVariation.Settings(FontVariation.weight(MEDIUM_WEIGHT)),
    ),
)
private val LeagueGothic = FontFamily(Font(R.font.league_gothic))

private val base = Typography()
private val META_SPACING = 0.04.em

val artikoTypography = Typography(
    displayLarge = base.displayLarge.copy(fontFamily = LeagueGothic, fontSize = 64.sp, lineHeight = 64.sp),
    displayMedium = base.displayMedium.copy(fontFamily = LeagueGothic, fontSize = 52.sp, lineHeight = 52.sp),
    displaySmall = base.displaySmall.copy(fontFamily = LeagueGothic, fontSize = 44.sp, lineHeight = 44.sp),
    headlineLarge = base.headlineLarge.copy(fontFamily = LeagueGothic, fontSize = 48.sp, lineHeight = 48.sp),
    headlineMedium = base.headlineMedium.copy(fontFamily = LeagueGothic, fontSize = 40.sp, lineHeight = 40.sp),
    headlineSmall = base.headlineSmall.copy(fontFamily = LeagueGothic, fontSize = 32.sp, lineHeight = 32.sp),
    titleLarge = base.titleLarge.copy(fontFamily = LeagueGothic, fontSize = 28.sp, lineHeight = 30.sp),
    titleMedium = base.titleMedium.copy(fontFamily = LeagueGothic, fontSize = 24.sp, lineHeight = 26.sp),
    titleSmall = base.titleSmall.copy(fontFamily = Inter, fontWeight = FontWeight.Medium),
    bodyLarge = base.bodyLarge.copy(fontFamily = Inter, fontSize = 16.sp, lineHeight = 26.sp),
    bodyMedium = base.bodyMedium.copy(fontFamily = Inter, fontSize = 15.sp, lineHeight = 24.sp),
    bodySmall = base.bodySmall.copy(fontFamily = Inter, fontSize = 13.sp, lineHeight = 20.sp),
    labelLarge = base.labelLarge.copy(fontFamily = Inter, fontWeight = FontWeight.Medium),
    labelMedium = base.labelMedium.copy(fontFamily = Inter, letterSpacing = META_SPACING),
    labelSmall = base.labelSmall.copy(fontFamily = Inter, letterSpacing = META_SPACING),
)
