package com.onlydevs.artiko.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

private val SMALL_CORNER = 8.dp
private val CARD_CORNER = 20.dp
private val LARGE_CORNER = 28.dp

val artikoShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(SMALL_CORNER),
    medium = RoundedCornerShape(CARD_CORNER),
    large = RoundedCornerShape(CARD_CORNER),
    extraLarge = RoundedCornerShape(LARGE_CORNER),
)
