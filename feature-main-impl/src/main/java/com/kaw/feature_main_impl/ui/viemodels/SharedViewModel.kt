package com.kaw.feature_main_impl.ui.viemodels

import com.kaw.feature_main_api.domain.models.MainScreenData
import kotlinx.coroutines.flow.StateFlow

interface SharedViewModel {
    val screenData: StateFlow<MainScreenData?>
    fun updateFavorite(vacancyId: String, isFavorite: Boolean)
    fun loadData()
}