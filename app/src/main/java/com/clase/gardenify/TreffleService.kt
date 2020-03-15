package com.clase.gardenify

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface TreffleService {
    @GET("/api/plants")
    fun listPlants(@Header("Authorization") token: String): Call<List<Plant?>?>?
}