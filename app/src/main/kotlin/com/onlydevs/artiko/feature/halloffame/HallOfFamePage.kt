package com.onlydevs.artiko.feature.halloffame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.data.Category
import com.onlydevs.artiko.data.HallOfFameEntry
import com.onlydevs.artiko.data.MediaKind
import com.onlydevs.artiko.data.Work
import com.onlydevs.artiko.ui.ArtCard
import com.onlydevs.artiko.ui.FEED_PADDING
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun HallOfFamePage(halls: List<CategoryHall>, onOpenArtView: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        contentPadding = PaddingValues(FEED_PADDING),
        verticalArrangement = Arrangement.spacedBy(FEED_SPACING),
    ) {
        item { Text("Hall of Fame", style = MaterialTheme.typography.headlineMedium) }
        if (halls.isEmpty()) {
            item { Text("No votes yet this month") }
        }
        halls.forEach { hall ->
            item(key = hall.category.id) {
                Text(hall.category.label, style = MaterialTheme.typography.titleLarge)
            }
            items(hall.entries, key = { it.work.id }) { entry ->
                Column(verticalArrangement = Arrangement.spacedBy(FEED_SPACING)) {
                    Text("#${entry.rank} with ${entry.votes} votes")
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
