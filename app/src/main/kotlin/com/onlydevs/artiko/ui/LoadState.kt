package com.onlydevs.artiko.ui

import kotlin.coroutines.cancellation.CancellationException

sealed interface LoadState<out T> {
    data object Loading : LoadState<Nothing>

    data class Failed(val message: String) : LoadState<Nothing>

    data class Loaded<T>(val data: T) : LoadState<T>
}

suspend fun <T> loadState(block: suspend () -> T): LoadState<T> = try {
    LoadState.Loaded(block())
} catch (cancellation: CancellationException) {
    throw cancellation
} catch (error: Exception) {
    LoadState.Failed(error.message ?: "Something went wrong")
}
