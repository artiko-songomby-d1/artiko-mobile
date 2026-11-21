package com.onlydevs.artiko.feature.exposition

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.data.Exposition
import com.onlydevs.artiko.ui.FeedList
import com.onlydevs.artiko.ui.ScreenHeader
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun ExpositionPage(content: ExpositionContent, onOpenArtView: (String) -> Unit, onBack: (() -> Unit)? = null) {
    FeedList(
        works = content.works,
        onOpenArtView = onOpenArtView,
        emptyMessage = "No art in this exposition yet",
    ) {
        ScreenHeader(title = content.exposition.title, onBack = onBack)
        Text(content.exposition.description)
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpositionPagePreview() {
    val exposition = Exposition("1", "1", "Rado", "Highlands", "Landscapes of the highlands", null, 0)
    ArtikoTheme {
        ExpositionPage(ExpositionContent(exposition, works = emptyList()), onOpenArtView = {})
    }
}
