package com.kaw.core_db_impl.data.source

import com.kaw.core_db_api.data.dao.FavoriteVacancyDao
import com.kaw.core_db_api.domain.entity.FavoriteVacancyEntity
import com.kaw.core_db_impl.domain.source.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val favoriteVacancyDao: FavoriteVacancyDao):
    LocalDataSource {

    override suspend fun insert(favoriteVacancy: FavoriteVacancyEntity) {
        favoriteVacancyDao.insert(favoriteVacancy)
    }

    override suspend fun delete(id: String) {
        favoriteVacancyDao.delete(id)
    }

    override suspend fun getAll(): List<FavoriteVacancyEntity> {
        return favoriteVacancyDao.getAll()
    }

    override suspend fun getById(id: String): FavoriteVacancyEntity?{
        return favoriteVacancyDao.getById(id)
    }
}