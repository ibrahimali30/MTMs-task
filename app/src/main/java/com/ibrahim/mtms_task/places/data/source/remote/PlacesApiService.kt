package com.ibrahim.mtms_task.places.data.source.remote

import com.ibrahim.mtms_task.places.data.model.character.PlacesResponse
import com.ibrahim.mtms_task.base.PLACES_API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {

    @GET("maps/api/place/nearbysearch/json")
    fun getPlaces(
        @Query("keyword") keyword:String,
        @Query("key") apikey:String = PLACES_API_KEY,
        @Query("location") location:String = "-33.8670522,151.1957362",
        @Query("radius") radius:Int = 1500
    ): Single<PlacesResponse>


}


