package com.kaw.feature_favorite_api.domain.usecases

import com.kaw.feature_favorite_api.domain.models.FavoriteScreenData
import kotlinx.coroutines.flow.Flow

interface GetFavoriteScreenDataUseCase {
    fun execute(): Flow<FavoriteScreenData>
}