package com.ibrahim.mtms_task.places.data.source.remote

import android.annotation.SuppressLint
import io.reactivex.Single
import com.ibrahim.mtms_task.places.domain.entity.PlacesParams
import com.ibrahim.mtms_task.model.LocationModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlacesRemoteDataSource @Inject constructor(
    private val placesApiService: PlacesApiService
) {

     @SuppressLint("CheckResult")
     fun fetchPlaces(params: PlacesParams): Single<List<LocationModel>> {
          placesApiService.getPlaces(
                 "te"
         )
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe({
it
              }, {
it
              })

         return Single.just(listOf())
     }

}