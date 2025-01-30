package com.kaw.feature_favorite_impl.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaw.feature_favorite_api.domain.models.FavoriteScreenData
import com.kaw.feature_favorite_api.domain.usecases.GetFavoriteScreenDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteScreenDataUseCase: GetFavoriteScreenDataUseCase
) : ViewModel() {
    private val _screenData = MutableStateFlow<FavoriteScreenData?>(null)
    val screenData: StateFlow<FavoriteScreenData?> = _screenData.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            getFavoriteScreenDataUseCase.execute()
                .collect { data ->
                    _screenData.value = data
                }
        }
    }
}