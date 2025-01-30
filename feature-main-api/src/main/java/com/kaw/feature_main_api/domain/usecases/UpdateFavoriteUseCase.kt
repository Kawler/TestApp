package com.kaw.feature_main_api.domain.usecases

import kotlinx.coroutines.flow.Flow

interface UpdateFavoriteUseCase {
    fun execute(vacancyId: String, isFavorite: Boolean): Flow<Unit>
}