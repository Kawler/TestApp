package com.kaw.feature_main_api.domain.models

data class Offer(
    val id: String?,
    val title: String?,
    val link: String?,
    val button: Button? = null
)