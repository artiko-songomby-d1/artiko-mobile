package com.onlydevs.artiko.feature.exposition

import com.onlydevs.artiko.data.Exposition
import com.onlydevs.artiko.data.Work

data class ExpositionContent(val exposition: Exposition, val works: List<Work>)
