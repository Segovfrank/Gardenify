package com.clase.gardenify

data class Plant(
    val slug: String,
    var link: String?,
    val scientific_name: String,
    val id: Int,
    val complete_data: Boolean,
    val common_name: String
)