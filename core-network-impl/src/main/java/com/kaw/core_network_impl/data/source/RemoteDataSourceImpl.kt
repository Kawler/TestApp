package com.kaw.core_network_impl.data.source

import com.kaw.core_network_api.data.dto.ResponseDto
import com.kaw.core_network_api.data.service.JobSearchService
import com.kaw.core_network_api.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: JobSearchService
) : RemoteDataSource {
    override fun getResponse(): Flow<ResponseDto> = flow {
        val response = apiService.getResponse()
        emit(response)
    }
}