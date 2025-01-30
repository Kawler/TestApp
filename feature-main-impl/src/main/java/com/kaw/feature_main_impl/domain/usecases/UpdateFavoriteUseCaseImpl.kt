package com.kaw.feature_main_impl.domain.usecases

import com.kaw.feature_main_api.domain.repository.MainRepository
import com.kaw.feature_main_api.domain.usecases.UpdateFavoriteUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateFavoriteUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
) : UpdateFavoriteUseCase {
    override fun execute(vacancyId: String, isFavorite: Boolean): Flow<Unit> {
        return mainRepository.updateFavorite(vacancyId, isFavorite)
    }
}