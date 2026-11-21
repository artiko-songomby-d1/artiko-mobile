package com.onlydevs.artiko.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class LoadingViewModel<T>(private val load: suspend () -> T) : ViewModel() {
    private val mutableState = MutableStateFlow<LoadState<T>>(LoadState.Loading)
    val state: StateFlow<LoadState<T>> = mutableState.asStateFlow()

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch {
            mutableState.value = LoadState.Loading
            mutableState.value = loadState(load)
        }
    }
}
