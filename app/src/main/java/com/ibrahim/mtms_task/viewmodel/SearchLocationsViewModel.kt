package com.ibrahim.mtms_task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.ibrahim.mtms_task.model.LocationModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchLocationsViewModel @Inject constructor(

): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val screenState by lazy { MutableLiveData<SearchScreenState>() }

    fun getSourceLocations(name: String = "") {
        //avoid duplicate
        screenState.value = SearchScreenState.Loading

        val db = FirebaseFirestore.getInstance()
        db.collection("Source").addSnapshotListener { value, error ->

            val response = value?.documents?.map {
                it.toObject(LocationModel::class.java)
            }?.filterNotNull()

            handleSuccessResponse(response)

            if (error != null)
                handleErrorResponse(error)
        }

    }


    private fun handleErrorResponse(it: Throwable) {
        screenState.value = SearchScreenState.ErrorLoadingFromApi(it)
    }

    private fun handleSuccessResponse(it: List<LocationModel>?) {
        screenState.value = SearchScreenState.SuccessAPIResponse(it!!)
    }


    sealed class SearchScreenState {
        object Loading : SearchScreenState()
        class SuccessAPIResponse(val data: List<LocationModel>) : SearchScreenState()
        class ErrorLoadingFromApi(val error: Throwable) : SearchScreenState()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        screenState.value = SearchScreenState.Loading
        super.onCleared()
    }
}