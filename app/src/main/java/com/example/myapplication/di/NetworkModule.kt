package com.example.myapplication.di

import com.example.myapplication.BuildConfig
import com.example.myapplication.data.network.RestApi
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val networkModule = module {

        single<OkHttpClient> {
            OkHttpClient.Builder()
                .apply {
                    if (BuildConfig.DEBUG) {
                        addInterceptor(
                            HttpLoggingInterceptor().setLevel(
                                HttpLoggingInterceptor.Level.BODY
                            )
                        )
                    }
                }
                .retryOnConnectionFailure(retryOnConnectionFailure = true)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build()
        }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/")
                .client(get())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
        }

        single<RestApi> {
            get<Retrofit>().create(RestApi::class.java)
        }
    }
}