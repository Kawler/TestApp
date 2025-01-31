package com.kaw.core_network_impl.domain.repository

import com.kaw.core_network_api.data.dto.ResponseDto
import com.kaw.core_network_api.domain.repository.CachedRepository
import com.kaw.core_network_api.domain.source.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CachedRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
): CachedRepository {
    private val _cachedResponse = MutableStateFlow<ResponseDto?>(null)

    private val _isDataLoaded = MutableStateFlow(false)

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        coroutineScope.launch {
            remoteDataSource.getResponse()
                .onEach { response ->
                    _cachedResponse.value = response
                    _isDataLoaded.value = true
                }
                .catch { exception ->
                    _isDataLoaded.value = true
                }
                .collect{}
        }
    }

    override fun getResponse(): Flow<ResponseDto> = flow {
        _isDataLoaded.filter { it }.take(1).first()

        _cachedResponse.value?.let { cached ->
            emit(cached)
        }
    }
}