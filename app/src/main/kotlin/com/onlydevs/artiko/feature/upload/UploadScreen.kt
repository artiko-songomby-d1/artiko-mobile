package com.onlydevs.artiko.feature.upload

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.onlydevs.artiko.data.WorkDraft
import com.onlydevs.artiko.data.displayName
import com.onlydevs.artiko.data.readMedia
import com.onlydevs.artiko.ui.LoadStateContent
import com.onlydevs.artiko.ui.artikoViewModel
import kotlinx.coroutines.launch

/** Publishing screen; [attachmentUri] is a file already chosen elsewhere, such as a fresh recording. */
@Composable
fun UploadScreen(attachmentUri: String? = null) {
    val viewModel = artikoViewModel { api -> UploadViewModel(api) }
    val options by viewModel.state.collectAsStateWithLifecycle()
    val publication by viewModel.publication.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var title by rememberSaveable { mutableStateOf("") }
    var story by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    var expositionId by rememberSaveable { mutableStateOf<String?>(null) }
    var mediaUri by rememberSaveable { mutableStateOf(attachmentUri) }
    val picker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { mediaUri = it.toString() }
    }
    val draft = WorkDraft(title, story, category, expositionId)

    LoadStateContent(state = options, onRetry = viewModel::reload) { loaded ->
        UploadPage(
            options = loaded,
            draft = draft,
            mediaName = mediaUri?.let { context.displayName(it.toUri()) },
            publication = publication,
            onDraftChange = {
                title = it.title
                story = it.story
                category = it.category
                expositionId = it.expositionId
            },
            onPickMedia = { picker.launch("*/*") },
            onPublish = { mediaUri?.let { scope.launch { viewModel.publish(draft, context.readMedia(it.toUri())) } } },
        )
    }
}
