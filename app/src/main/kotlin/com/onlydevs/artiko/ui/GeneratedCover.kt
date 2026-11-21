package com.onlydevs.artiko.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.theme.ArtikoTheme
import kotlin.random.Random

private val PALETTES = listOf(
    listOf(Color(0xFFE76F51), Color(0xFFF4A261), Color(0xFF264653)),
    listOf(Color(0xFF2A9D8F), Color(0xFFE9C46A), Color(0xFF1E2B4A)),
    listOf(Color(0xFFB5838D), Color(0xFFFFCDB2), Color(0xFF6D6875)),
    listOf(Color(0xFF606C38), Color(0xFFDDA15E), Color(0xFF283618)),
    listOf(Color(0xFF3D5A80), Color(0xFFEE6C4D), Color(0xFFE0FBFC)),
)
private const val SHAPE_COUNT = 5
private const val SHAPE_ALPHA = 0.8f
private const val MIN_RADIUS_SHARE = 0.15f
private const val RADIUS_SPREAD_SHARE = 0.3f
private const val RECT_WIDTH_FACTOR = 1.6f
private const val RECT_HEIGHT_FACTOR = 1.1f

/** Abstract artwork drawn from a seed, so a work without media still gets a stable, colorful cover. */
@Composable
fun GeneratedCover(seed: String, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val random = Random(seed.hashCode())
        val palette = PALETTES[random.nextInt(PALETTES.size)]
        drawRect(Brush.linearGradient(listOf(palette[2], palette[0])))
        repeat(SHAPE_COUNT) {
            val color = palette[random.nextInt(palette.size)].copy(alpha = SHAPE_ALPHA)
            val radius = size.minDimension * (MIN_RADIUS_SHARE + random.nextFloat() * RADIUS_SPREAD_SHARE)
            val center = Offset(size.width * random.nextFloat(), size.height * random.nextFloat())
            if (random.nextBoolean()) {
                drawCircle(color, radius, center)
            } else {
                val rectSize = Size(radius * RECT_WIDTH_FACTOR, radius * RECT_HEIGHT_FACTOR)
                drawRect(color, topLeft = center - Offset(radius, radius), size = rectSize)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GeneratedCoverPreview() {
    ArtikoTheme {
        GeneratedCover(seed = "sunrise")
    }
}
