package com.kaw.core_network_api.data.service

import com.kaw.core_network_api.data.dto.ResponseDto
import retrofit2.http.GET

interface JobSearchService {
    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download/")
    suspend fun getResponse(): ResponseDto
}