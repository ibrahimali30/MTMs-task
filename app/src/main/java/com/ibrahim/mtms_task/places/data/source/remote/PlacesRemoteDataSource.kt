package com.ibrahim.mtms_task.places.data.source.remote

import android.annotation.SuppressLint
import io.reactivex.Single
import com.ibrahim.mtms_task.places.domain.entity.PlacesParams
import com.ibrahim.mtms_task.places.data.model.places.PlacesResponse
import javax.inject.Inject

class PlacesRemoteDataSource @Inject constructor(
    private val placesApiService: PlacesApiService
) {

     @SuppressLint("CheckResult")
     fun fetchPlaces(params: PlacesParams): Single<PlacesResponse> {
         return placesApiService.getPlaces(
             query = params.query

         )
     }

}