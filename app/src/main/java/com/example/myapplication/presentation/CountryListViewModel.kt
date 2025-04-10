package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.useCases.GetCountriesUseCase
import com.example.myapplication.domain.utils.onFailure
import com.example.myapplication.domain.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryListViewModel(
    private val getCountriesUseCase: GetCountriesUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    fun loadCountries() {
        viewModelScope.launch {
            _screenState.emit(ScreenState.Loading)
            getCountriesUseCase.execute()
                .onSuccess {
                    _screenState.emit(ScreenState.Success(it))
                }
                .onFailure {
                    _screenState.emit(ScreenState.Failed(it.localizedMessage.orEmpty()))
                }
        }
    }
}