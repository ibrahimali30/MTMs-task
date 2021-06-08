package com.ibrahim.mtms_task.places.data.repository

import io.reactivex.Single
import com.ibrahim.mtms_task.places.data.source.remote.PlacesRemoteDataSource
import com.ibrahim.mtms_task.places.domain.entity.PlacesParams
import com.ibrahim.mtms_task.model.PlaceUiModel
import com.ibrahim.mtms_task.places.domain.repsitory.PlacesRepository
import com.ibrahim.mtms_task.places.presentation.mapper.mapToPlaceUidModel


import javax.inject.Inject


class PlacesRepositoryImpl @Inject constructor(
    private val placesRemoteDataSource: PlacesRemoteDataSource
) : PlacesRepository {

    override fun fetchDestinationPlaces(params: PlacesParams): Single<List<PlaceUiModel>> {
        return placesRemoteDataSource.fetchDestinationPlaces(params).map {
            it.mapToPlaceUidModel()
        }
    }

    override fun fetchSourcePlaces(query: String): Single<List<PlaceUiModel>> {
        return placesRemoteDataSource.fetchSourcePlaces(query)
    }


}

