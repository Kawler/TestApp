package com.kaw.core_db_api.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kaw.core_db_api.domain.entity.FavoriteVacancyEntity

@Dao
interface FavoriteVacancyDao {
    @Insert
    suspend fun insert(favoriteVacancy: FavoriteVacancyEntity)

    @Query("DELETE FROM FavoriteVacancyEntity WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM FavoriteVacancyEntity")
    suspend fun getAll(): List<FavoriteVacancyEntity>

    @Query("SELECT * FROM FavoriteVacancyEntity WHERE id = :id")
    suspend fun getById(id: String): FavoriteVacancyEntity?
}