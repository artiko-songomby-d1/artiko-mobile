package com.onlydevs.artiko.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val SCREEN_PADDING = 16.dp
private val SECTION_SPACING = 12.dp
private val CARD_PADDING = 16.dp
private val CARD_ELEVATION = 6.dp

@Composable
fun PlaceholderScreen(title: String, sections: List<PlaceholderSection>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(SCREEN_PADDING),
        verticalArrangement = Arrangement.spacedBy(SECTION_SPACING),
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineMedium)
        sections.forEach { section -> SectionCard(section) }
    }
}

@Composable
private fun SectionCard(section: PlaceholderSection) {
    val elevation = CardDefaults.cardElevation(defaultElevation = CARD_ELEVATION)
    val label: @Composable ColumnScope.() -> Unit = {
        Text(text = section.label, modifier = Modifier.padding(CARD_PADDING))
    }
    if (section.onClick == null) {
        Card(modifier = Modifier.fillMaxWidth(), elevation = elevation, content = label)
    } else {
        Card(
            onClick = rememberHapticClick(section.onClick),
            modifier = Modifier.fillMaxWidth(),
            elevation = elevation,
            content = label,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaceholderScreenPreview() {
    ArtikoTheme {
        PlaceholderScreen(title = "Title", sections = listOf(PlaceholderSection("Section")))
    }
}
