package com.kaw.effectivemobile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaw.effectivemobile.data.repository.FavoriteCountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    favoriteCountRepository: FavoriteCountRepository
) : ViewModel() {

    val favoriteCount: StateFlow<Int> = favoriteCountRepository.getFavoriteCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )
}