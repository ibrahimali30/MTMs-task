package com.ibrahim.mtms_task.places.data.source.remote

import com.ibrahim.mtms_task.places.data.model.places.PlacesResponse
import com.ibrahim.mtms_task.base.PLACES_API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {

    @GET("maps/api/place/textsearch/json")
    fun getPlaces(
        @Query("query") query: String,
        @Query("key") apikey: String,
        @Query("radius") radius: Int
    ): Single<PlacesResponse>


}


