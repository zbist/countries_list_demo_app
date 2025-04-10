package com.example.myapplication.di

import com.example.myapplication.domain.useCases.GetCountriesUseCase
import org.koin.dsl.module

object UseCasesModule {
    val useCasesModule = module {
        factory<GetCountriesUseCase> {
            GetCountriesUseCase(get())
        }
    }
}