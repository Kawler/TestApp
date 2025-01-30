package com.kaw.feature_favorite_api.domain.repository

import com.kaw.feature_favorite_api.domain.models.FavoriteScreenData
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteScreenData(): Flow<FavoriteScreenData>
    fun updateFavorite(vacancyId: String, isFavorite: Boolean): Flow<Unit>
}