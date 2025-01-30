package com.kaw.feature_favorite_impl.domain.usecases

import com.kaw.feature_favorite_api.domain.models.FavoriteScreenData
import com.kaw.feature_favorite_api.domain.repository.FavoriteRepository
import com.kaw.feature_favorite_api.domain.usecases.GetFavoriteScreenDataUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetFavoriteScreenDataUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
): GetFavoriteScreenDataUseCase {
    override fun execute(): Flow<FavoriteScreenData> {
        return favoriteRepository.getFavoriteScreenData()
    }
}