package com.kaw.core_network_impl.di

import com.kaw.core_network_api.data.service.JobSearchService
import com.kaw.core_network_api.domain.source.RemoteDataSource
import com.kaw.core_network_impl.data.remote.RetrofitClient
import com.kaw.core_network_impl.data.source.RemoteDataSourceImpl
import com.kaw.core_network_impl.domain.repository.CachedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
    }

    @Provides
    fun provideJobSearchService(retrofitClient: RetrofitClient): JobSearchService {
        return retrofitClient.service
    }
    @Provides
    fun provideRemoteDataSource(service: JobSearchService): RemoteDataSource {
        return RemoteDataSourceImpl(service)
    }
    @Singleton
    @Provides
    fun provideCachedRepository(remoteDataSource: RemoteDataSource): CachedRepository {
        return CachedRepository(remoteDataSource)
    }

}