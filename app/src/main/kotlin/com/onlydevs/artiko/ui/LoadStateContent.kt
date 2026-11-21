package com.onlydevs.artiko.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val ERROR_PADDING = 16.dp

@Composable
fun <T> LoadStateContent(
    state: LoadState<T>,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit,
) {
    when (state) {
        LoadState.Loading -> Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        is LoadState.Failed -> Column(
            modifier = modifier.fillMaxSize().padding(ERROR_PADDING),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(state.message)
            ArtikoButton(text = "Retry", onClick = onRetry)
        }

        is LoadState.Loaded -> content(state.data)
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadStateContentFailedPreview() {
    ArtikoTheme {
        LoadStateContent<Unit>(state = LoadState.Failed("Offline"), onRetry = {}) {}
    }
}
