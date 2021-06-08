package com.ibrahim.mtms_task.places.domain.interactor

import com.ibrahim.mtms_task.model.PlaceUiModel
import com.ibrahim.mtms_task.places.domain.entity.PlacesParams
import com.ibrahim.mtms_task.places.domain.repsitory.PlacesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(private val placesRepository: PlacesRepository) {

    fun fetchDestinationPlaces(params: PlacesParams): Single<List<PlaceUiModel>> {
        return placesRepository.fetchDestinationPlaces(params)
    }

    fun fetchSourcePlaces(query: String): Single<List<PlaceUiModel>> {
        return placesRepository.fetchSourcePlaces(query)
    }

}