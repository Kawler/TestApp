package com.kaw.feature_main_api.domain.usecases

import com.kaw.feature_main_api.domain.models.MainScreenData
import kotlinx.coroutines.flow.Flow

interface GetMainScreenDataUseCase {
    fun execute(): Flow<MainScreenData>
}