package com.onlydevs.artiko.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val HEIGHT = 48.dp
private val DOT_SIZE = 38.dp
private val DOT_INSET = 5.dp
private val START_PADDING = 22.dp
private val GAP = 12.dp
private val ARROW_SIZE = 16.dp
private const val DISABLED_ALPHA = 0.4f

/** The call to action of artiko-web: an ink pill with a red dot that holds an arrow. */
@Composable
fun ArtikoButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    val interactionSource = remember { MutableInteractionSource() }
    Surface(
        onClick = rememberHapticClick(onClick),
        enabled = enabled,
        modifier = modifier
            .height(HEIGHT)
            .alpha(if (enabled) 1f else DISABLED_ALPHA)
            .pressScale(interactionSource),
        interactionSource = interactionSource,
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        Row(
            modifier = Modifier.padding(start = START_PADDING, end = DOT_INSET),
            horizontalArrangement = Arrangement.spacedBy(GAP),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = text, style = MaterialTheme.typography.labelLarge)
            Box(
                modifier = Modifier.size(DOT_SIZE).background(MaterialTheme.colorScheme.secondary, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(ARROW_SIZE),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArtikoButtonPreview() {
    ArtikoTheme {
        ArtikoButton(text = "Start the visit", onClick = {})
    }
}
