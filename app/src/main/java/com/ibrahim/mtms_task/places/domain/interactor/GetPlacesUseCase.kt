package com.ibrahim.mtms_task.places.domain.interactor

import com.ibrahim.mtms_task.places.domain.entity.PlacesParams
import com.ibrahim.mtms_task.places.domain.repsitory.PlacesRepository
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(private val placesRepository: PlacesRepository) {

    fun fetchPlaces(params: PlacesParams) = placesRepository.fetchPlaces(params)

    fun fetchPlacesSubCategories(params: PlacesParams) = placesRepository.fetchPlacesSubCategories(params)

}