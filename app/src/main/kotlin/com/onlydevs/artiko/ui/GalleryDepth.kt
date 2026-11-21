package com.onlydevs.artiko.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.abs

private const val MAX_TILT_DEGREES = 18f
private const val SCALE_DROP = 0.1f
private const val FADE = 0.3f
private const val CAMERA_DISTANCE = 16f

data class DepthPose(val rotationX: Float, val scale: Float, val alpha: Float)

/** Items above the center lean back, items below lean forward, and both shrink and fade like a drum of pictures. */
fun depthPose(itemCenter: Float, viewportCenter: Float, viewportHeight: Float): DepthPose {
    val offset = ((itemCenter - viewportCenter) / (viewportHeight / 2f)).coerceIn(-1f, 1f)
    val depth = abs(offset)
    return DepthPose(rotationX = -offset * MAX_TILT_DEGREES, scale = 1f - depth * SCALE_DROP, alpha = 1f - depth * FADE)
}

/** Gives the item with [key] a perspective that depends on where it sits in the visible list. */
fun Modifier.galleryDepth(listState: LazyListState, key: Any): Modifier = graphicsLayer {
    val layout = listState.layoutInfo
    val item = layout.visibleItemsInfo.firstOrNull { it.key == key } ?: return@graphicsLayer
    val pose = depthPose(
        itemCenter = item.offset + item.size / 2f,
        viewportCenter = (layout.viewportStartOffset + layout.viewportEndOffset) / 2f,
        viewportHeight = layout.viewportSize.height.toFloat(),
    )
    rotationX = pose.rotationX
    scaleX = pose.scale
    scaleY = pose.scale
    alpha = pose.alpha
    cameraDistance = CAMERA_DISTANCE * density
}
