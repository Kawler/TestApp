package com.kaw.core_network_impl.domain.repository

import com.kaw.core_network_api.data.dto.ResponseDto
import com.kaw.core_network_api.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CachedRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {
    private val _cachedResponse = MutableStateFlow<ResponseDto?>(null)
    val cachedResponse: StateFlow<ResponseDto?> = _cachedResponse

    fun getResponse(): Flow<ResponseDto> = flow {
        if(_cachedResponse.value != null){
            emit(_cachedResponse.value!!)
        } else {
            remoteDataSource.getResponse().collect{ response ->
                _cachedResponse.value = response
                emit(response)
            }
        }
    }
}