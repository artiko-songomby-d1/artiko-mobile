package com.onlydevs.artiko.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private const val LIT_SHARE = 0.35f
private const val SHADED_SHARE = 0.1f
private const val VIGNETTE_ALPHA = 0.14f

/** The wall every screen hangs on: lit from the top, darker toward the floor, with a soft vignette. */
@Composable
fun GalleryWall(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val wall = MaterialTheme.colorScheme.background
    val lit = lerp(wall, Color.White, LIT_SHARE)
    val shaded = lerp(wall, Color.Black, SHADED_SHARE)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(lit, wall, shaded)))
            .background(Brush.radialGradient(listOf(Color.Transparent, Color.Black.copy(alpha = VIGNETTE_ALPHA)))),
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun GalleryWallPreview() {
    ArtikoTheme {
        GalleryWall { Text("On the wall") }
    }
}
