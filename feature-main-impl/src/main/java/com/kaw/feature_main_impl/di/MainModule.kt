package com.kaw.feature_main_impl.di

import com.kaw.feature_main_api.domain.repository.MainRepository
import com.kaw.feature_main_api.domain.usecases.GetMainScreenDataUseCase
import com.kaw.feature_main_api.domain.usecases.UpdateFavoriteUseCase
import com.kaw.feature_main_impl.data.repository.MainRepositoryImpl
import com.kaw.feature_main_impl.domain.usecases.GetMainScreenDataUseCaseImpl
import com.kaw.feature_main_impl.domain.usecases.UpdateFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainModule {

    @Binds
    abstract fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun bindGetMainScreenDataUseCase(getMainScreenDataUseCaseImpl: GetMainScreenDataUseCaseImpl): GetMainScreenDataUseCase

    @Binds
    abstract fun bindUpdateFavoriteUseCase(updateFavoriteUseCaseImpl: UpdateFavoriteUseCaseImpl): UpdateFavoriteUseCase
}