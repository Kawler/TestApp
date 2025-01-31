package com.kaw.core_db_impl.data.source

import com.kaw.core_db_api.data.dao.FavoriteVacancyDao
import com.kaw.core_db_api.domain.entity.FavoriteVacancyEntity
import com.kaw.core_db_api.domain.source.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val favoriteVacancyDao: FavoriteVacancyDao):
    LocalDataSource {

    override suspend fun insert(favoriteVacancy: FavoriteVacancyEntity) {
        if (!favoriteVacancy.isFavorite) {
            favoriteVacancyDao.delete(favoriteVacancy.id)
        } else {
            favoriteVacancyDao.insert(favoriteVacancy)
        }
    }

    override suspend fun delete(id: String) {
        favoriteVacancyDao.delete(id)
    }

    override fun getAll(): Flow<List<FavoriteVacancyEntity>> {
        return favoriteVacancyDao.getAll()
    }

    override suspend fun getById(id: String): FavoriteVacancyEntity?{
        return favoriteVacancyDao.getById(id)
    }
}