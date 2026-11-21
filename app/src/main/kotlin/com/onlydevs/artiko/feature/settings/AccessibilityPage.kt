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
import com.onlydevs.artiko.data.settings.TextSize
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val SIZE_LABELS = mapOf(
    TextSize.Small to R.string.text_size_small,
    TextSize.Normal to R.string.text_size_normal,
    TextSize.Large to R.string.text_size_large,
    TextSize.ExtraLarge to R.string.text_size_extra_large,
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AccessibilityPage(settings: AppSettings, onChange: ((AppSettings) -> AppSettings) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(stringResource(R.string.text_size_title), style = MaterialTheme.typography.titleLarge)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextSize.entries.forEach { size ->
                ExpositionChip(
                    title = stringResource(SIZE_LABELS.getValue(size)),
                    onClick = { onChange { it.copy(textSize = size) } },
                    selected = settings.textSize == size,
                )
            }
        }
        SettingsToggle(
            label = stringResource(R.string.reduce_motion),
            checked = settings.reduceMotion,
            onChange = { value -> onChange { it.copy(reduceMotion = value) } },
        )
        SettingsToggle(
            label = stringResource(R.string.flat_mode),
            checked = settings.flatMode,
            onChange = { value -> onChange { it.copy(flatMode = value) } },
        )
        SettingsToggle(
            label = stringResource(R.string.haptics),
            checked = settings.haptics,
            onChange = { value -> onChange { it.copy(haptics = value) } },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AccessibilityPagePreview() {
    ArtikoTheme {
        AccessibilityPage(settings = AppSettings(), onChange = {})
    }
}
