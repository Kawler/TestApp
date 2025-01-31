package com.kaw.feature_favorite_impl.domain.usecases

import com.kaw.feature_favorite_api.domain.repository.FavoriteRepository
import com.kaw.feature_favorite_api.domain.usecases.UpdateFavoriteUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateFavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : UpdateFavoriteUseCase {
    override fun execute(vacancyId: String, isFavorite: Boolean): Flow<Unit> {
        return favoriteRepository.updateFavorite(vacancyId, isFavorite)
    }
}