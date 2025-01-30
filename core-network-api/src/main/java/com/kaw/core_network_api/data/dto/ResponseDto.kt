package com.kaw.core_network_api.data.dto

data class ResponseDto(
    val offers: List<OfferDto>,
    val vacancies: List<VacancyDto>
)