package com.onlydevs.artiko.feature.artview

import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.data.Work
import com.onlydevs.artiko.ui.LoadingViewModel

class ArtViewViewModel(api: ArtikoApi, workId: String) : LoadingViewModel<Work>({ api.getWork(workId) })
