package com.example.myapplication.data

import com.example.myapplication.data.network.RestApi
import com.example.myapplication.domain.Repository
import com.example.myapplication.domain.models.Country
import com.example.myapplication.domain.utils.Either
import com.example.myapplication.domain.utils.toLeft
import com.example.myapplication.domain.utils.toRight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepository(
    private val restApi: RestApi
) : Repository {

    override suspend fun getCountries(): Either<Exception, List<Country>> {
        return withContext(Dispatchers.IO) {
            try {
                restApi.getCountries().toRight()
            } catch (e: Exception) {
                e.toLeft()
            }
        }
    }
}