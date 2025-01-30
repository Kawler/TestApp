package com.kaw.effectivemobile.data.repository

import com.kaw.core_network_impl.domain.repository.CachedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteCountRepository @Inject constructor(
    private val cachedRepository: CachedRepository,
) {
    fun getFavoriteCount(): Flow<Int> =
        cachedRepository.getResponse().map { response ->
            response.vacancies.count { it.isFavorite }
        }
}