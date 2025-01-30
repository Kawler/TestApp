package com.kaw.core_network_api.data.dto

import com.squareup.moshi.Json

data class VacancyDto (
    val id: String,
    @Json(name = "lookingNumber")
    val lookingNumber: Int?,
    val title: String,
    val address: AddressDto,
    val company: String,
    val experience: ExperienceDto,
    @Json(name = "publishedDate")
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryDto,
    val schedules: List<String>,
    val description: String,
    val responsibilities: String?,
    val questions: List<String>?
)