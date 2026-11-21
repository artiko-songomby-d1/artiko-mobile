package com.onlydevs.artiko.feature.halloffame

import com.onlydevs.artiko.data.Category
import com.onlydevs.artiko.data.HallOfFameEntry

data class CategoryHall(val category: Category, val entries: List<HallOfFameEntry>)
