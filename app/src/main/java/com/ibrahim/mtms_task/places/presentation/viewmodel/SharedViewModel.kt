package com.ibrahim.mtms_task.places.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition

class SharedViewModel(): ViewModel() {


    var searchQueryLiveData = MutableLiveData<String>()

    fun onQueryTextChanged(query: String) {
        searchQueryLiveData.value = query
    }

}