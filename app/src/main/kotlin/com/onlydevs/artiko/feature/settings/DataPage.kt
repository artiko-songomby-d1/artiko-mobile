package com.onlydevs.artiko.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.R
import com.onlydevs.artiko.data.settings.AppSettings
import com.onlydevs.artiko.ui.ArtikoButton
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun DataPage(
    settings: AppSettings,
    onChange: ((AppSettings) -> AppSettings) -> Unit,
    cacheCleared: Boolean,
    onClearCache: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SettingsToggle(
            label = stringResource(R.string.data_saver),
            hint = stringResource(R.string.data_saver_hint),
            checked = settings.dataSaver,
            onChange = { value -> onChange { it.copy(dataSaver = value) } },
        )
        ArtikoButton(text = stringResource(R.string.clear_cache), onClick = onClearCache)
        if (cacheCleared) Text(stringResource(R.string.cache_cleared), style = ArtikoText.meta)
    }
}

@Preview(showBackground = true)
@Composable
private fun DataPagePreview() {
    ArtikoTheme {
        DataPage(settings = AppSettings(), onChange = {}, cacheCleared = true, onClearCache = {})
    }
}
