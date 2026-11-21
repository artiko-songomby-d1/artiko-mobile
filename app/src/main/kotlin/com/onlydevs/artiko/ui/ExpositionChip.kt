package com.onlydevs.artiko.ui

import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun ExpositionChip(title: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    AssistChip(onClick = onClick, label = { Text(title) }, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
private fun ExpositionChipPreview() {
    ArtikoTheme {
        ExpositionChip(title = "Hall of Fame", onClick = {})
    }
}
