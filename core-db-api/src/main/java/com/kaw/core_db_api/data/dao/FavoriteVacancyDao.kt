package com.kaw.core_db_api.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaw.core_db_api.domain.entity.FavoriteVacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteVacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteVacancy: FavoriteVacancyEntity)

    @Query("DELETE FROM FavoriteVacancyEntity WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM FavoriteVacancyEntity")
    fun getAll(): Flow<List<FavoriteVacancyEntity>>

    @Query("SELECT * FROM FavoriteVacancyEntity WHERE id = :id")
    suspend fun getById(id: String): FavoriteVacancyEntity?
}