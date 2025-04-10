package com.example.myapplication.presentation

import com.example.myapplication.domain.models.Country

sealed interface ScreenState {
    data object Loading : ScreenState
    data class Failed(val message: String) : ScreenState

    data class Success(
        val items: List<Country>
    ) : ScreenState
}