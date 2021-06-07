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

    override fun fetchPlaces(params: PlacesParams): Single<List<PlaceUiModel>> {
        return placesRemoteDataSource.fetchPlaces(params).map {
            it.mapToPlaceUidModel()
        }
    }

    override fun fetchPlacesSubCategories(params: PlacesParams): Single<List<PlaceUiModel>> {
        return placesRemoteDataSource.fetchPlaces(params).map {
            it.mapToPlaceUidModel()
        }
    }


}

