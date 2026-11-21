package com.onlydevs.artiko.feature.discovery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.FeedList
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun DiscoveryPage(
    content: DiscoveryContent,
    onOpenHallOfFame: () -> Unit,
    onOpenExposition: (String) -> Unit,
    onOpenArtView: (String) -> Unit,
) {
    FeedList(works = content.works, onOpenArtView = onOpenArtView, emptyMessage = "No art yet") {
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
            onOpenHallOfFame = {},
            onOpenExposition = {},
            onOpenArtView = {},
        )
    }
}
