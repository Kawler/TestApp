package com.kaw.feature_main_api.domain.repository

import com.kaw.feature_main_api.domain.models.MainScreenData
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getMainScreenData(): Flow<MainScreenData>
    fun updateFavorite(vacancyId: String, isFavorite: Boolean): Flow<Unit>
}