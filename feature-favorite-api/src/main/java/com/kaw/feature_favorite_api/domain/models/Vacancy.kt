package com.kaw.feature_favorite_api.domain.models

data class Vacancy(
    val id: String?,
    val lookingNumber: Int?,
    val title: String?,
    val address: Address?,
    val company: String?,
    val experience: Experience?,
    val publishedDate: String?,
    val isFavorite: Boolean,
    val salary: Salary?,
    val schedules: List<String>?,
    val description: String?,
    val responsibilities: String?,
    val questions: List<String>?
)