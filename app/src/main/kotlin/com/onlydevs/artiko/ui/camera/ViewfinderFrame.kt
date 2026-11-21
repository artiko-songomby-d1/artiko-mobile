package com.onlydevs.artiko.ui.camera

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

private val INSET = 24.dp
private val ARM = 40.dp
private val STROKE = 4.dp

/** Four white corner brackets that frame what the camera is looking at. */
@Composable
fun ViewfinderFrame(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val inset = INSET.toPx()
        val arm = ARM.toPx()
        val stroke = Stroke(width = STROKE.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        fun corner(x: Float, y: Float, towardX: Float, towardY: Float) {
            val path = Path().apply {
                moveTo(x, y + towardY * arm)
                lineTo(x, y)
                lineTo(x + towardX * arm, y)
            }
            drawPath(path, Color.White, style = stroke)
        }
        corner(inset, inset, 1f, 1f)
        corner(size.width - inset, inset, -1f, 1f)
        corner(inset, size.height - inset, 1f, -1f)
        corner(size.width - inset, size.height - inset, -1f, -1f)
    }
}
