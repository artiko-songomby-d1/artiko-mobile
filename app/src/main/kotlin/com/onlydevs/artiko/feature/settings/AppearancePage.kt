package com.onlydevs.artiko.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.R
import com.onlydevs.artiko.data.settings.AppSettings
import com.onlydevs.artiko.data.settings.ThemeMode
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val THEME_LABELS = mapOf(
    ThemeMode.System to R.string.theme_system,
    ThemeMode.Light to R.string.theme_light,
    ThemeMode.Dark to R.string.theme_dark,
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppearancePage(settings: AppSettings, onChange: ((AppSettings) -> AppSettings) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(stringResource(R.string.theme_title), style = MaterialTheme.typography.titleLarge)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ThemeMode.entries.forEach { mode ->
                ExpositionChip(
                    title = stringResource(THEME_LABELS.getValue(mode)),
                    onClick = { onChange { it.copy(theme = mode) } },
                    selected = settings.theme == mode,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppearancePagePreview() {
    ArtikoTheme {
        AppearancePage(settings = AppSettings(), onChange = {})
    }
}
