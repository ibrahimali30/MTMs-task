package com.ibrahim.mtms_task.places.data.model.places

data class PlacesResponse(
    val html_attributions: List<Any>,
    val results: List<Result>,
    val status: String
)