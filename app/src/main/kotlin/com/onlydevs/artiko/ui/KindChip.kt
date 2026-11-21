package com.onlydevs.artiko.ui

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

private val HORIZONTAL_PADDING = 12.dp
private val VERTICAL_PADDING = 5.dp

/** The small uppercase pill that labels a work on artiko-web. */
@Composable
fun KindChip(label: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.secondary) {
    Surface(modifier = modifier, shape = CircleShape, color = color, contentColor = Color.White) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = HORIZONTAL_PADDING, vertical = VERTICAL_PADDING),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun KindChipPreview() {
    ArtikoTheme {
        KindChip(label = "Painting")
    }
}
