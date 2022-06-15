package com.example.themoviedb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Base Url de la API
class RetrofitInstance {
    companion object {
        const val baseUrl = "https://api.themoviedb.org/3/movie/"
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("${baseUrl}")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}