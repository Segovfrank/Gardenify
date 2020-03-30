package com.clase.gardenify

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface DogService {
    @GET("/breeds")
    fun listBreeds(@Header("x-api-key") key: String): Call<List<Breed?>?>?
}