package com.krithi.frauddetector.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "https://safebrowsing.googleapis.com/"

    val api: SafeBrowsingApi by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SafeBrowsingApi::class.java)
    }
}