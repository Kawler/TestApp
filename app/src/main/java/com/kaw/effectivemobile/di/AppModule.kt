package com.kaw.effectivemobile.di

import com.kaw.effectivemobile.data.repository.FavoriteCountRepositoryImpl
import com.kaw.effectivemobile.domain.repository.FavoriteCountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindFavoriteCountRepository(impl: FavoriteCountRepositoryImpl): FavoriteCountRepository
}