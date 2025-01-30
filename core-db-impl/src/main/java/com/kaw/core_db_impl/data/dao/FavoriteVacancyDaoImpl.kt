package com.kaw.core_db_impl.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaw.core_db_api.data.dao.FavoriteVacancyDao
import com.kaw.core_db_api.domain.entity.FavoriteVacancyEntity

@Dao
interface FavoriteVacancyDaoImpl : FavoriteVacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(favoriteVacancy: FavoriteVacancyEntity)

    @Query("DELETE FROM FavoriteVacancyEntity WHERE id = :id")
    override suspend fun delete(id: String)

    @Query("SELECT * FROM FavoriteVacancyEntity")
    override suspend fun getAll(): List<FavoriteVacancyEntity>

    @Query("SELECT * FROM FavoriteVacancyEntity WHERE id = :id")
    override suspend fun getById(id: String): FavoriteVacancyEntity?
}