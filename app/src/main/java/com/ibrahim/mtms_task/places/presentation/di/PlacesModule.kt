package com.ibrahim.mtms_task.places.presentation.di


import com.ibrahim.mtms_task.places.data.repository.PlacesRepositoryImpl
import com.ibrahim.mtms_task.places.data.source.remote.PlacesApiService
import com.ibrahim.mtms_task.places.domain.repsitory.PlacesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class PlacesModule {

    @Provides
    fun providesPlacesRepository(placesRepositoryImpl: PlacesRepositoryImpl): PlacesRepository = placesRepositoryImpl

    @Provides
    fun providesPlacesApiService(builder: Retrofit.Builder): PlacesApiService {
        return builder.build().create(PlacesApiService::class.java)
    }


}