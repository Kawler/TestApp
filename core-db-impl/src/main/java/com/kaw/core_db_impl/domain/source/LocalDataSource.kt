package com.kaw.core_db_impl.domain.source

import com.kaw.core_db_api.domain.entity.FavoriteVacancyEntity

interface LocalDataSource {
    suspend fun insert(favoriteVacancy: FavoriteVacancyEntity)

    suspend fun delete(id: String)

    suspend fun getAll(): List<FavoriteVacancyEntity>

    suspend fun getById(id: String): FavoriteVacancyEntity?
}