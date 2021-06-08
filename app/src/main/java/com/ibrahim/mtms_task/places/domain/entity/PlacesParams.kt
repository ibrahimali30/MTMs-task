package com.ibrahim.mtms_task.places.domain.entity

import com.ibrahim.mtms_task.base.PLACES_API_KEY

data class PlacesParams(
    val query: String = "",
    val apiKey: String = PLACES_API_KEY,
    val radius: Int = 1500
)