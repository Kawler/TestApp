package com.kaw.core_db_api.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteVacancyEntity(
    @PrimaryKey
    val id: String,
    val isFavorite: Boolean
)