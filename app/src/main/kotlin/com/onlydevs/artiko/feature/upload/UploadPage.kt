package com.onlydevs.artiko.feature.upload

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.data.Work
import com.onlydevs.artiko.data.WorkDraft
import com.onlydevs.artiko.ui.ArtikoButton
import com.onlydevs.artiko.ui.ArtikoTextField
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.FEED_PADDING
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.LoadState
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme

/** The publishing form, in the four steps of the maquette: file, details, category and exposition. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UploadPage(
    options: UploadOptions,
    draft: WorkDraft,
    mediaName: String?,
    publication: LoadState<Work>?,
    onDraftChange: (WorkDraft) -> Unit,
    onPickMedia: () -> Unit,
    onPublish: () -> Unit,
) {
    val ready = mediaName != null && draft.title.isNotBlank() && draft.category.isNotEmpty()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(FEED_PADDING),
        verticalArrangement = Arrangement.spacedBy(FEED_SPACING),
    ) {
        Text("Upload", style = MaterialTheme.typography.headlineMedium)

        StepTitle("01", "Input")
        ExpositionChip(title = mediaName ?: "Choose a file", onClick = onPickMedia, selected = mediaName != null)

        StepTitle("02", "Details")
        ArtikoTextField(draft.title, { onDraftChange(draft.copy(title = it)) }, label = "Title")
        ArtikoTextField(draft.story, { onDraftChange(draft.copy(story = it)) }, label = "Story", singleLine = false)

        StepTitle("03", "Category")
        FlowRow(horizontalArrangement = Arrangement.spacedBy(FEED_SPACING)) {
            options.categories.forEach { category ->
                ExpositionChip(
                    title = category.label,
                    onClick = { onDraftChange(draft.copy(category = category.id)) },
                    selected = draft.category == category.id,
                )
            }
        }

        StepTitle("04", "Exposition")
        FlowRow(horizontalArrangement = Arrangement.spacedBy(FEED_SPACING)) {
            ExpositionChip(
                title = "None",
                onClick = { onDraftChange(draft.copy(expositionId = null)) },
                selected = draft.expositionId == null,
            )
            options.expositions.forEach { exposition ->
                ExpositionChip(
                    title = exposition.title,
                    onClick = { onDraftChange(draft.copy(expositionId = exposition.id)) },
                    selected = draft.expositionId == exposition.id,
                )
            }
        }

        ArtikoButton(text = "Publish", onClick = onPublish, enabled = ready && publication != LoadState.Loading)
        PublicationStatus(publication)
    }
}

@Composable
private fun StepTitle(number: String, title: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(FEED_SPACING), verticalAlignment = Alignment.Bottom) {
        Text(number, style = ArtikoText.number)
        Text(title, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
private fun PublicationStatus(publication: LoadState<Work>?) {
    when (publication) {
        null -> Unit
        LoadState.Loading -> Text("Publishing...", style = ArtikoText.meta)
        is LoadState.Failed -> Text(publication.message, style = ArtikoText.meta)
        is LoadState.Loaded -> Text("Published: ${publication.data.title}", style = ArtikoText.meta)
    }
}

@Preview(showBackground = true)
@Composable
private fun UploadPagePreview() {
    ArtikoTheme {
        UploadPage(
            options = UploadOptions(emptyList(), emptyList()),
            draft = WorkDraft("", "", "", null),
            mediaName = null,
            publication = null,
            onDraftChange = {},
            onPickMedia = {},
            onPublish = {},
        )
    }
}
