package com.ibrahim.mtms_task.places.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.mtms_task.places.domain.entity.PlacesParams
import com.ibrahim.mtms_task.model.LocationModel
import com.ibrahim.mtms_task.places.domain.interactor.GetPlacesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class PlacesAnysViewModel @Inject constructor(
    private val refreshForecastUseCase: GetPlacesUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val screenState by lazy { MutableLiveData<ScreenState>() }

    var offset: Int = -1
    fun getSourceLocations(s: String) {


        screenState.value = ScreenState.Loading
        val params = PlacesParams()
        refreshForecastUseCase.fetchPlaces(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccessResponse(it)
            }, {
                handleErrorResponse(it)
            }).addTo(compositeDisposable)
    }


    fun handleErrorResponse(it: Throwable) {
        offset = -1
        screenState.value = ScreenState.ErrorLoadingFromApi(it)
    }

    private fun handleSuccessResponse(it: List<LocationModel>) {
        screenState.value = ScreenState.SuccessAPIResponse(it)
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        class SuccessAPIResponse(val data: List<LocationModel>) : ScreenState()
        class ErrorLoadingFromApi(val error: Throwable) : ScreenState()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}