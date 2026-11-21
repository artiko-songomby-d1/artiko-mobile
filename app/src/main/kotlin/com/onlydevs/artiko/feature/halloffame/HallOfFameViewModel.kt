package com.onlydevs.artiko.feature.halloffame

import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.ui.LoadingViewModel
import java.time.YearMonth
import java.time.ZoneOffset
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class HallOfFameViewModel(api: ArtikoApi, month: String = currentMonth()) :
    LoadingViewModel<List<CategoryHall>>({ loadHallOfFame(api, month) })

private fun currentMonth(): String = YearMonth.now(ZoneOffset.UTC).toString()

private suspend fun loadHallOfFame(api: ArtikoApi, month: String): List<CategoryHall> = coroutineScope {
    api.listCategories()
        .map { category -> async { CategoryHall(category, api.getHallOfFame(category.id, month)) } }
        .awaitAll()
        .filter { it.entries.isNotEmpty() }
}
