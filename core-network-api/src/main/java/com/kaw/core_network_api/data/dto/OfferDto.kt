package com.kaw.core_network_api.data.dto

data class OfferDto(
    val id: String,
    val title: String,
    val link: String,
    val button: ButtonDto? = null
)