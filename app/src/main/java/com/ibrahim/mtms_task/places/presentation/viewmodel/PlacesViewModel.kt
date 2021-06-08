package com.ibrahim.mtms_task.places.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.ibrahim.mtms_task.places.domain.entity.PlacesParams
import com.ibrahim.mtms_task.model.PlaceUiModel
import com.ibrahim.mtms_task.places.domain.interactor.GetPlacesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class PlacesViewModel @Inject constructor(
    private val refreshForecastUseCase: GetPlacesUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val screenState by lazy { MutableLiveData<ScreenState>() }

    fun getDestinationLocations(searchQuery: String) {

        screenState.value = ScreenState.Loading
        val params = PlacesParams(searchQuery)
        refreshForecastUseCase.fetchDestinationPlaces(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccessResponse(it)
            }, {
                handleErrorResponse(it)
            }).addTo(compositeDisposable)
    }

    fun getSourceLocations(query: String) {

        screenState.value = ScreenState.Loading

        refreshForecastUseCase.fetchSourcePlaces(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccessResponse(it)
            }, {
                handleErrorResponse(it)
            }).addTo(compositeDisposable)

    }


    private fun handleErrorResponse(it: Throwable) {
        screenState.value = ScreenState.ErrorLoadingFromApi(it)
    }

    private fun handleSuccessResponse(it: List<PlaceUiModel>) {
        screenState.value = ScreenState.SuccessAPIResponse(it)
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        class SuccessAPIResponse(val data: List<PlaceUiModel>) : ScreenState()
        class ErrorLoadingFromApi(val error: Throwable) : ScreenState()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}