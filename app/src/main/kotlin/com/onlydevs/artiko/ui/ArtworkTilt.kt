package com.onlydevs.artiko.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.onlydevs.artiko.ui.theme.LocalAppSettings
import kotlinx.coroutines.launch

private const val MAX_TILT_DEGREES = 25f
private const val DEGREES_PER_PIXEL = 0.15f
private const val CAMERA_DISTANCE = 16f
private const val SHEEN_ALPHA = 0.28f
private const val SHEEN_HALF_WIDTH_SHARE = 0.3f

fun nextTilt(current: Float, dragPixels: Float): Float =
    (current + dragPixels * DEGREES_PER_PIXEL).coerceIn(-MAX_TILT_DEGREES, MAX_TILT_DEGREES)

/** Turns the artwork like a picture on a pivot while dragging sideways, with a sheen that follows the light. */
@Composable
fun Modifier.tiltOnDrag(): Modifier {
    if (!LocalAppSettings.current.depthEffects) return this
    val tilt = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val settle: () -> Unit = { scope.launch { tilt.animateTo(0f, spring()) } }
    return this
        .pointerInput(Unit) {
            detectHorizontalDragGestures(onDragEnd = { settle() }, onDragCancel = { settle() }) { _, drag ->
                scope.launch { tilt.snapTo(nextTilt(tilt.value, drag)) }
            }
        }
        .graphicsLayer {
            rotationY = tilt.value
            cameraDistance = CAMERA_DISTANCE * density
        }
        .drawWithContent {
            drawContent()
            val center = size.width / 2f + size.width * (tilt.value / MAX_TILT_DEGREES) / 2f
            val half = size.width * SHEEN_HALF_WIDTH_SHARE
            val sheen = listOf(Color.Transparent, Color.White.copy(alpha = SHEEN_ALPHA), Color.Transparent)
            drawRect(Brush.horizontalGradient(sheen, startX = center - half, endX = center + half))
        }
}
