package com.onlydevs.artiko.feature.artist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.data.Artist
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.FeedList
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun ArtistPage(content: ArtistContent, onOpenExposition: (String) -> Unit, onOpenArtView: (String) -> Unit) {
    FeedList(works = content.works, onOpenArtView = onOpenArtView) {
        Text(content.artist.displayName, style = MaterialTheme.typography.headlineMedium)
        Text(content.artist.region, style = MaterialTheme.typography.labelLarge)
        Text(content.artist.bio)
        Text("Expositions", style = MaterialTheme.typography.titleMedium)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(FEED_SPACING)) {
            items(content.expositions, key = { it.id }) { exposition ->
                ExpositionChip(title = exposition.title, onClick = { onOpenExposition(exposition.id) })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArtistPagePreview() {
    val artist = Artist("1", "Rado", "Weaver", "Antsirabe")
    ArtikoTheme {
        ArtistPage(ArtistContent(artist, emptyList(), emptyList()), onOpenExposition = {}, onOpenArtView = {})
    }
}
