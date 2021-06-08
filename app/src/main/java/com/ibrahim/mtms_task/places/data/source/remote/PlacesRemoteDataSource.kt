package com.ibrahim.mtms_task.places.data.source.remote

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore
import com.ibrahim.mtms_task.model.PlaceUiModel
import io.reactivex.Single
import com.ibrahim.mtms_task.places.domain.entity.PlacesParams
import com.ibrahim.mtms_task.places.data.model.places.PlacesResponse
import javax.inject.Inject

class PlacesRemoteDataSource @Inject constructor(
    private val placesApiService: PlacesApiService
) {

     @SuppressLint("CheckResult")
     fun fetchDestinationPlaces(params: PlacesParams): Single<PlacesResponse> {
         return placesApiService.getPlaces(
             query = params.query,
             radius = params.radius,
             apikey = params.apiKey

         )
     }

    @SuppressLint("CheckResult")
     fun fetchSourcePlaces(query: String): Single<List<PlaceUiModel>> {
         return Single.create {emitter->
             val db = FirebaseFirestore.getInstance()
             db.collection("Source")
                 .orderBy("name")
                 .startAt(query)
                 .endAt(query + "\uf8ff")
                 .addSnapshotListener { value, error ->

                 val response = value?.documents?.map {
                     it.toObject(PlaceUiModel::class.java)
                 }?.filterNotNull()

                 response?.let {
                     emitter.onSuccess(it)
                 }

                 if (error != null)
                     emitter.onError(error)
             }
         }
     }

}