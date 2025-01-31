package com.kaw.core_db_impl.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaw.core_db_api.data.dao.FavoriteVacancyDao
import com.kaw.core_db_api.domain.entity.FavoriteVacancyEntity

@Database(entities = [FavoriteVacancyEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteVacancyDao(): FavoriteVacancyDao
}