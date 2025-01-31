package com.kaw.core_db_impl.di

import android.content.Context
import androidx.room.Room
import com.kaw.core_db_api.data.dao.FavoriteVacancyDao
import com.kaw.core_db_impl.data.db.AppDatabase
import com.kaw.core_db_impl.data.source.LocalDataSourceImpl
import com.kaw.core_db_api.domain.source.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DbModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource


    companion object {
        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "job_search_db"
            ).build()
        }

        @Provides
        fun provideFavoriteVacancyDao(database: AppDatabase): FavoriteVacancyDao {
            return database.favoriteVacancyDao()
        }
    }
}