package com.ibrahim.mtms_task.places.domain.repsitory


import com.ibrahim.mtms_task.places.domain.entity.PlacesParams
import com.ibrahim.mtms_task.model.PlaceUiModel
import io.reactivex.Single

interface PlacesRepository {
    fun fetchDestinationPlaces(params: PlacesParams): Single<List<PlaceUiModel>>
    fun fetchSourcePlaces(query: String): Single<List<PlaceUiModel>>
}