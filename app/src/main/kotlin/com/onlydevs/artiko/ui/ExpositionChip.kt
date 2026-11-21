package com.onlydevs.artiko.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val BORDER_WIDTH = 1.5.dp
private val HORIZONTAL_PADDING = 14.dp
private val VERTICAL_PADDING = 8.dp

/** An outlined pill, the tag style of artiko-web. */
@Composable
fun ExpositionChip(title: String, onClick: () -> Unit, modifier: Modifier = Modifier, selected: Boolean = false) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
        contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
        border = BorderStroke(BORDER_WIDTH, MaterialTheme.colorScheme.outline),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = HORIZONTAL_PADDING, vertical = VERTICAL_PADDING),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpositionChipPreview() {
    ArtikoTheme {
        ExpositionChip(title = "Hall of Fame", onClick = {})
    }
}
