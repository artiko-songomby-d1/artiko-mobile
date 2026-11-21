package com.onlydevs.artiko.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.onlydevs.artiko.R

private val SANS_WEIGHTS = listOf(FontWeight.Normal, FontWeight.Medium, FontWeight.SemiBold, FontWeight.Bold)
private val SERIF_WEIGHTS = listOf(FontWeight.Medium, FontWeight.SemiBold)

// Fonts of artiko-web: Bricolage Grotesque for text, Cormorant italic for accents, League Gothic for titles.
private val Sans = FontFamily(
    SANS_WEIGHTS.map { weight ->
        Font(
            resId = R.font.bricolage_grotesque,
            weight = weight,
            variationSettings = FontVariation.Settings(FontVariation.weight(weight.weight)),
        )
    },
)
private val Serif = FontFamily(
    SERIF_WEIGHTS.map { weight ->
        Font(
            resId = R.font.cormorant_italic,
            weight = weight,
            style = FontStyle.Italic,
            variationSettings = FontVariation.Settings(FontVariation.weight(weight.weight)),
        )
    },
)
private val Display = FontFamily(Font(R.font.league_gothic))

private val base = Typography()
private val CHIP_SPACING = 0.08.em

val artikoTypography = Typography(
    displayLarge = base.displayLarge.copy(fontFamily = Display, fontSize = 64.sp, lineHeight = 64.sp),
    displayMedium = base.displayMedium.copy(fontFamily = Display, fontSize = 56.sp, lineHeight = 56.sp),
    displaySmall = base.displaySmall.copy(fontFamily = Display, fontSize = 48.sp, lineHeight = 48.sp),
    headlineLarge = base.headlineLarge.copy(fontFamily = Display, fontSize = 56.sp, lineHeight = 56.sp),
    headlineMedium = base.headlineMedium.copy(fontFamily = Display, fontSize = 48.sp, lineHeight = 48.sp),
    headlineSmall = base.headlineSmall.copy(fontFamily = Display, fontSize = 36.sp, lineHeight = 36.sp),
    titleLarge = base.titleLarge.copy(fontFamily = Display, fontSize = 32.sp, lineHeight = 32.sp),
    titleMedium = base.titleMedium.copy(
        fontFamily = Serif,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 26.sp,
    ),
    titleSmall = base.titleSmall.copy(fontFamily = Sans, fontWeight = FontWeight.Medium),
    bodyLarge = base.bodyLarge.copy(fontFamily = Sans, fontSize = 17.sp, lineHeight = 27.sp),
    bodyMedium = base.bodyMedium.copy(fontFamily = Sans, fontSize = 15.5.sp, lineHeight = 24.5.sp),
    bodySmall = base.bodySmall.copy(fontFamily = Sans, fontSize = 13.sp, lineHeight = 20.sp),
    labelLarge = base.labelLarge.copy(fontFamily = Sans, fontSize = 15.sp, fontWeight = FontWeight.SemiBold),
    labelMedium = base.labelMedium.copy(fontFamily = Sans, fontSize = 14.sp, fontWeight = FontWeight.Medium),
    labelSmall = base.labelSmall.copy(
        fontFamily = Sans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = CHIP_SPACING,
    ),
)

/** Text styles of artiko-web that Material has no slot for; their colors follow the light or dark theme. */
object ArtikoText {
    val meta: TextStyle
        @Composable get() = TextStyle(
            fontFamily = Serif,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Medium,
            fontSize = 19.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    val number: TextStyle
        @Composable get() = TextStyle(
            fontFamily = Serif,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.tertiary,
        )
    val activeTab: TextStyle
        @Composable get() = TextStyle(
            fontFamily = Serif,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.tertiary,
        )
}
