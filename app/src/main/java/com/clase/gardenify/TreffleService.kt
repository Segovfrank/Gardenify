package com.clase.gardenify

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface TreffleService {
    //https://trefle.io/some-url?token=YOUR-TOKEN
    @GET("/api/plants")
    fun listPlants(@Header("Authorization") token: String): Call<List<Plant?>?>?

    @GET("/api/plants/{id}")
    fun getPlantById(@Header("Authorization") token: String, @Path("id") id: Int): Call<PlantDetails?>?
}