package com.ibrahim.mtms_task.places.presentation.mapper

import com.ibrahim.mtms_task.model.PlaceUiModel
import com.ibrahim.mtms_task.places.data.model.places.PlacesResponse
import com.ibrahim.mtms_task.places.data.model.places.Result

fun PlacesResponse.mapToPlaceUidModel(): List<PlaceUiModel>? {
   return results.map {
       it.mapToPlaceUiModel()
   }
}

private fun Result.mapToPlaceUiModel(): PlaceUiModel {
    return PlaceUiModel(
        name,
        geometry.location.lat,
        geometry.location.lng
    )
}
