package com.iesam.huellaskotlintrainning.app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dam.sitehub.es/projects/huellas/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var apiService: ApiService = retrofit.create(ApiService::class.java)
}