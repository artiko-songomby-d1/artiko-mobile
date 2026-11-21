package com.onlydevs.artiko.feature.discovery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.FeedList
import com.onlydevs.artiko.ui.SearchField
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun DiscoveryPage(
    content: DiscoveryContent,
    query: String,
    onQueryChange: (String) -> Unit,
    onOpenHallOfFame: () -> Unit,
    onOpenExposition: (String) -> Unit,
    onOpenArtView: (String) -> Unit,
) {
    val emptyMessage = if (query.isBlank()) "No art yet" else "No art matches your search"
    FeedList(works = content.works, onOpenArtView = onOpenArtView, emptyMessage = emptyMessage) {
        SearchField(value = query, onValueChange = onQueryChange)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(FEED_SPACING)) {
            item { ExpositionChip(title = "Hall of Fame", onClick = onOpenHallOfFame) }
            items(content.expositions, key = { it.id }) { exposition ->
                ExpositionChip(title = exposition.title, onClick = { onOpenExposition(exposition.id) })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DiscoveryPagePreview() {
    ArtikoTheme {
        DiscoveryPage(
            content = DiscoveryContent(emptyList(), emptyList()),
            query = "",
            onQueryChange = {},
            onOpenHallOfFame = {},
            onOpenExposition = {},
            onOpenArtView = {},
        )
    }
}
