package com.onlydevs.artiko.feature.upload

import androidx.lifecycle.viewModelScope
import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.data.MediaFile
import com.onlydevs.artiko.data.Work
import com.onlydevs.artiko.data.WorkDraft
import com.onlydevs.artiko.data.workParts
import com.onlydevs.artiko.ui.LoadState
import com.onlydevs.artiko.ui.LoadingViewModel
import com.onlydevs.artiko.ui.loadState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val SIGN_IN_MESSAGE = "Sign in as an artist to publish. Accounts are coming to the app."

class UploadViewModel(private val api: ArtikoApi) : LoadingViewModel<UploadOptions>({ loadOptions(api) }) {
    private val mutablePublication = MutableStateFlow<LoadState<Work>?>(null)

    /** Null until the visitor publishes. */
    val publication: StateFlow<LoadState<Work>?> = mutablePublication.asStateFlow()

    fun publish(draft: WorkDraft, media: MediaFile) {
        viewModelScope.launch {
            mutablePublication.value = LoadState.Loading
            mutablePublication.value = explainSignIn(loadState { api.createWork(workParts(draft, media)) })
        }
    }

    fun dismiss() {
        mutablePublication.value = null
    }
}

private suspend fun loadOptions(api: ArtikoApi): UploadOptions = coroutineScope {
    val categories = async { api.listCategories() }
    val expositions = async { api.listExpositions() }
    UploadOptions(categories.await(), expositions.await())
}

private fun explainSignIn(result: LoadState<Work>): LoadState<Work> =
    if (result is LoadState.Failed && "401" in result.message) LoadState.Failed(SIGN_IN_MESSAGE) else result
