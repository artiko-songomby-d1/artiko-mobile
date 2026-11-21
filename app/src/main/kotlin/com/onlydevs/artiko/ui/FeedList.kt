package com.onlydevs.artiko.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.data.Work

val FEED_PADDING = 16.dp
val FEED_SPACING = 12.dp

@Composable
fun FeedList(
    works: List<Work>,
    onOpenArtView: (String) -> Unit,
    modifier: Modifier = Modifier,
    emptyMessage: String = "Nothing to show yet",
    header: @Composable () -> Unit = {},
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize().statusBarsPadding(),
        contentPadding = PaddingValues(FEED_PADDING),
        verticalArrangement = Arrangement.spacedBy(FEED_SPACING),
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(FEED_SPACING)) { header() }
        }
        if (works.isEmpty()) {
            item { EmptyPlaceholder(emptyMessage) }
        }
        items(works, key = { it.id }) { work ->
            ArtCard(
                work = work,
                onClick = { onOpenArtView(work.id) },
                modifier = Modifier.galleryDepth(listState, work.id),
            )
        }
    }
}
