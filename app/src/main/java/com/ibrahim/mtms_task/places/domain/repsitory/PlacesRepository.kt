package com.ibrahim.mtms_task.places.domain.repsitory


import com.ibrahim.mtms_task.places.domain.entity.PlacesParams
import com.ibrahim.mtms_task.model.LocationModel
import io.reactivex.Single

interface PlacesRepository {
    fun fetchPlaces(params: PlacesParams): Single<List<LocationModel>>
    fun fetchPlacesSubCategories(params: PlacesParams): Single<List<LocationModel>>
}