package com.onlydevs.artiko.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val DEFAULT_HEIGHT = 160.dp

@Composable
fun EmptyPlaceholder(message: String, modifier: Modifier = Modifier, height: Dp = DEFAULT_HEIGHT) {
    OutlinedCard(modifier = modifier.fillMaxWidth().height(height)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = message,
                style = ArtikoText.meta,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyPlaceholderPreview() {
    ArtikoTheme {
        EmptyPlaceholder(message = "No art yet")
    }
}
