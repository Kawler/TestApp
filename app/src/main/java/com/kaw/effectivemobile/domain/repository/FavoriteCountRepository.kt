package com.kaw.effectivemobile.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoriteCountRepository {
    fun getFavoriteCount(): Flow<Int>
}