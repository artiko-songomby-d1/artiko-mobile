package com.onlydevs.artiko.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.onlydevs.artiko.ArtikoApplication
import com.onlydevs.artiko.data.ArtikoApi

@Composable
inline fun <reified VM : ViewModel> artikoViewModel(noinline create: (ArtikoApi) -> VM): VM {
    val application = LocalContext.current.applicationContext as ArtikoApplication
    return viewModel(factory = viewModelFactory { initializer { create(application.api) } })
}
