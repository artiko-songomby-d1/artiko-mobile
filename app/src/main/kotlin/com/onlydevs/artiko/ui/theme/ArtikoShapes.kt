package com.onlydevs.artiko.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

private val SMALL_CORNER = 4.dp
private val CORNER = 6.dp
private val LARGE_CORNER = 8.dp

val artikoShapes = Shapes(
    extraSmall = RoundedCornerShape(SMALL_CORNER),
    small = RoundedCornerShape(CORNER),
    medium = RoundedCornerShape(CORNER),
    large = RoundedCornerShape(LARGE_CORNER),
    extraLarge = RoundedCornerShape(LARGE_CORNER),
)
