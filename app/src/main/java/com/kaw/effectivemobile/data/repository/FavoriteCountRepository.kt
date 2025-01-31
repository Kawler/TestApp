package com.kaw.effectivemobile.data.repository

import com.kaw.core_db_api.domain.source.LocalDataSource
import com.kaw.core_network_api.domain.repository.CachedRepository
import com.kaw.effectivemobile.domain.repository.FavoriteCountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

class FavoriteCountRepositoryImpl @Inject constructor(
    private val cachedRepository: CachedRepository,
    private val localDataSource: LocalDataSource
):FavoriteCountRepository {
    override fun getFavoriteCount(): Flow<Int> =
        cachedRepository.getResponse()
            .combine(localDataSource.getAll()) { response, localFavorites ->
                response.vacancies.count { vacancyDto ->
                    val localFavorite = localFavorites.find { it.id == vacancyDto.id }
                    localFavorite?.isFavorite ?: vacancyDto.isFavorite
                }
            }
}