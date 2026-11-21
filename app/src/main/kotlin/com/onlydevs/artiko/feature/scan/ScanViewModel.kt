package com.onlydevs.artiko.feature.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.data.MediaFile
import com.onlydevs.artiko.data.ScanResult
import com.onlydevs.artiko.data.asPart
import com.onlydevs.artiko.ui.LoadState
import com.onlydevs.artiko.ui.loadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScanViewModel(private val api: ArtikoApi) : ViewModel() {
    private val mutableState = MutableStateFlow<LoadState<ScanResult>?>(null)

    /** Null while nothing was scanned yet. */
    val state: StateFlow<LoadState<ScanResult>?> = mutableState.asStateFlow()

    fun scan(photo: MediaFile) {
        viewModelScope.launch {
            mutableState.value = LoadState.Loading
            mutableState.value = loadState { api.scanImage(photo.asPart("file")) }
        }
    }

    fun reset() {
        mutableState.value = null
    }
}
