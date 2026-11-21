package com.onlydevs.artiko.feature.halloffame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.data.Category
import com.onlydevs.artiko.data.HallOfFameEntry
import com.onlydevs.artiko.data.MediaKind
import com.onlydevs.artiko.data.Work
import com.onlydevs.artiko.ui.ArtCard
import com.onlydevs.artiko.ui.EmptyPlaceholder
import com.onlydevs.artiko.ui.FEED_PADDING
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.ScreenHeader
import com.onlydevs.artiko.ui.galleryDepth
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val RANK_SPACING = 10.dp

@Composable
fun HallOfFamePage(halls: List<CategoryHall>, onOpenArtView: (String) -> Unit, onBack: (() -> Unit)? = null) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        contentPadding = PaddingValues(FEED_PADDING),
        verticalArrangement = Arrangement.spacedBy(FEED_SPACING),
    ) {
        item { ScreenHeader(title = "Hall of Fame", onBack = onBack) }
        if (halls.isEmpty()) {
            item { EmptyPlaceholder("No votes yet this month") }
        }
        halls.forEach { hall ->
            item(key = hall.category.id) {
                Text(hall.category.label, style = MaterialTheme.typography.titleLarge)
            }
            items(hall.entries, key = { it.work.id }) { entry ->
                Column(
                    modifier = Modifier.galleryDepth(listState, entry.work.id),
                    verticalArrangement = Arrangement.spacedBy(FEED_SPACING),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(RANK_SPACING),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text("%02d".format(entry.rank), style = ArtikoText.number)
                        Text("${entry.votes} votes", style = ArtikoText.meta)
                    }
                    ArtCard(work = entry.work, onClick = { onOpenArtView(entry.work.id) })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HallOfFamePagePreview() {
    val work = Work("1", "1", "Rado", "Sunrise", "", "painting", "", MediaKind.Video, true, 3)
    ArtikoTheme {
        HallOfFamePage(
            halls = listOf(CategoryHall(Category("painting", "Painting"), listOf(HallOfFameEntry(1, work, 3)))),
            onOpenArtView = {},
        )
    }
}
