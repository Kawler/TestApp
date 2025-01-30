package com.kaw.feature_favorite_api.domain.usecases

import kotlinx.coroutines.flow.Flow

interface UpdateFavoriteUseCase {
    fun execute(vacancyId: String, isFavorite: Boolean): Flow<Unit>
}