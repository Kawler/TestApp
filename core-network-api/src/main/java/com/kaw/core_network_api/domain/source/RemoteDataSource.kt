package com.kaw.core_network_api.domain.source

import com.kaw.core_network_api.data.dto.ResponseDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getResponse(): Flow<ResponseDto>
}