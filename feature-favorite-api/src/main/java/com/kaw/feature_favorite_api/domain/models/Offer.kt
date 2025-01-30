package com.kaw.feature_favorite_api.domain.models

data class Offer(
    val id: String?,
    val title: String?,
    val link: String?,
    val button: Button? = null
)