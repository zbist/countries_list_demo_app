package com.example.myapplication.domain

import com.example.myapplication.domain.models.Country
import com.example.myapplication.domain.utils.Either

interface Repository {
    suspend fun getCountries(): Either<Exception, List<Country>>
}