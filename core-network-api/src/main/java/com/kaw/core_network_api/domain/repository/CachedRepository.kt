package com.kaw.core_network_api.domain.repository

import com.kaw.core_network_api.data.dto.ResponseDto
import kotlinx.coroutines.flow.Flow


interface CachedRepository {
    fun getResponse(): Flow<ResponseDto>
}