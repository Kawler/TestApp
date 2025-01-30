package com.kaw.feature_main_impl.domain.usecases

import com.kaw.feature_main_api.domain.models.MainScreenData
import com.kaw.feature_main_api.domain.repository.MainRepository
import com.kaw.feature_main_api.domain.usecases.GetMainScreenDataUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMainScreenDataUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
): GetMainScreenDataUseCase {
    override fun execute(): Flow<MainScreenData> {
        return mainRepository.getMainScreenData()
    }
}