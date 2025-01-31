package com.kaw.core_db_api.domain.source

import com.kaw.core_db_api.domain.entity.FavoriteVacancyEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insert(favoriteVacancy: FavoriteVacancyEntity)

    suspend fun delete(id: String)

    fun getAll(): Flow<List<FavoriteVacancyEntity>>

    suspend fun getById(id: String): FavoriteVacancyEntity?
}