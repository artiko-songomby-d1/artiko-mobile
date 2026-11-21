package com.onlydevs.artiko.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.R
import com.onlydevs.artiko.ui.FEED_PADDING
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.SectionRow
import com.onlydevs.artiko.ui.ScreenHeader
import com.onlydevs.artiko.ui.theme.ArtikoTheme

/** The list of settings sections; each row opens its own page. */
@Composable
fun SettingsScreen(onOpen: (SettingsSection) -> Unit, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding().padding(FEED_PADDING),
        verticalArrangement = Arrangement.spacedBy(FEED_SPACING),
    ) {
        ScreenHeader(title = stringResource(R.string.settings_title), onBack = onBack)
        SettingsSection.entries.forEach { section ->
            SectionRow(label = stringResource(section.title), onClick = { onOpen(section) })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    ArtikoTheme {
        SettingsScreen(onOpen = {}, onBack = {})
    }
}
