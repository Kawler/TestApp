package com.kaw.feature_main_impl.ui.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaw.feature_main_api.domain.models.MainScreenData
import com.kaw.feature_main_api.domain.usecases.GetMainScreenDataUseCase
import com.kaw.feature_main_api.domain.usecases.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenSharedViewModel @Inject constructor(
    private val getMainScreenDataUseCase: GetMainScreenDataUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : ViewModel(), SharedViewModel {
    private val _screenData = MutableStateFlow<MainScreenData?>(null)
    override val screenData: StateFlow<MainScreenData?> = _screenData.asStateFlow()

    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch {
            getMainScreenDataUseCase.execute()
                .collect { data ->
                    _screenData.value = data
                }
        }
    }

    override fun updateFavorite(vacancyId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            updateFavoriteUseCase.execute(vacancyId, isFavorite).collect{}
            val currentData = _screenData.value ?: return@launch
            val updatedVacancies = currentData.vacancies!!.map { vacancy ->
                if(vacancy.id == vacancyId){
                    vacancy.copy(isFavorite = isFavorite)
                }else {
                    vacancy
                }
            }
            _screenData.value = currentData.copy(vacancies = updatedVacancies)
        }
    }
}