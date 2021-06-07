package com.ibrahim.mtms_task.places.data.model.character

import com.ibrahim.mtms_task.places.data.model.character.places.Result

data class PlacesResponse(
    val html_attributions: List<Any>,
    val results: List<Result>,
    val status: String
)