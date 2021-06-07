package com.ibrahim.mtms_task.model

data class LocationModel(
        val name: String,
        val lat: Double,
        val lon: Double,
){
    constructor(): this("",0.0,0.0)
}