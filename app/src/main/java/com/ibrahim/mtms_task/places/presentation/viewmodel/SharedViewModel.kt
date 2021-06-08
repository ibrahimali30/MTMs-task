package com.ibrahim.mtms_task.places.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.ibrahim.mtms_task.model.PlaceUiModel

class SharedViewModel(): ViewModel() {


    var searchQueryLiveData = MutableLiveData<String>()
    var selectedSourceLiveData = MutableLiveData<PlaceUiModel>()
    var selectedDestinationLiveData = MutableLiveData<PlaceUiModel>()

    fun onQueryTextChanged(query: String) {
        searchQueryLiveData.value = query
    }

    fun onSourceItemClicked(location: PlaceUiModel) {
        selectedSourceLiveData.value = location
    }

    fun onDestinationClicked(location: PlaceUiModel) {
        selectedDestinationLiveData.value = location
    }

}