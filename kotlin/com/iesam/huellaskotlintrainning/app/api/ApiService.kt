package com.iesam.huellaskotlintrainning.app.api

import com.iesam.huellaskotlintrainning.domain.Cat
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("cats.json")
    fun getCats(): Call<List<Cat>>
}