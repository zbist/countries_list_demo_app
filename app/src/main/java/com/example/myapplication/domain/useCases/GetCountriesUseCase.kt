package com.example.myapplication.domain.useCases

import com.example.myapplication.domain.Repository
import com.example.myapplication.domain.models.Country
import com.example.myapplication.domain.utils.Either

class GetCountriesUseCase(private val repository: Repository) {

    suspend fun execute(): Either<Exception, List<Country>> {
        return repository.getCountries()
    }
}