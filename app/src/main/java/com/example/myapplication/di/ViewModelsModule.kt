package com.example.myapplication.di

import com.example.myapplication.presentation.CountryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelsModule {
    val viewModelsModule = module {
        viewModel<CountryListViewModel> {
            CountryListViewModel(get())
        }
    }
}