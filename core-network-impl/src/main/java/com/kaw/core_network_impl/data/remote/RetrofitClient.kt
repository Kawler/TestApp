package com.kaw.core_network_impl.data.remote

import com.kaw.core_network_api.data.service.JobSearchService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitClient @Inject constructor(){

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service: JobSearchService = retrofit.create(JobSearchService::class.java)
}