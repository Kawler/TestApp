package com.kaw.feature_favorite_impl.di

import com.kaw.feature_favorite_api.domain.repository.FavoriteRepository
import com.kaw.feature_favorite_api.domain.usecases.GetFavoriteScreenDataUseCase
import com.kaw.feature_favorite_api.domain.usecases.UpdateFavoriteUseCase
import com.kaw.feature_favorite_impl.data.repository.FavoriteRepositoryImpl
import com.kaw.feature_favorite_impl.domain.usecases.GetFavoriteScreenDataUseCaseImpl
import com.kaw.feature_favorite_impl.domain.usecases.UpdateFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class FavoriteModule {

    @Binds
    abstract fun bindFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    abstract fun bindGetFavoriteScreenDataUseCase(getMainScreenDataUseCaseImpl: GetFavoriteScreenDataUseCaseImpl): GetFavoriteScreenDataUseCase

    @Binds
    abstract fun bindUpdateFavoriteUseCase(updateFavoriteUseCaseImpl: UpdateFavoriteUseCaseImpl): UpdateFavoriteUseCase
}