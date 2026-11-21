package com.onlydevs.artiko.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.R
import com.onlydevs.artiko.ui.theme.ArtikoTheme

/** The title of a screen with the back button on its left; [onBack] null means a screen without one. */
@Composable
fun ScreenHeader(title: String, onBack: (() -> Unit)?, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (onBack != null) {
            IconButton(onClick = rememberHapticClick(onBack)) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
            }
        }
        Text(title, style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenHeaderPreview() {
    ArtikoTheme {
        ScreenHeader(title = "Settings", onBack = {})
    }
}
