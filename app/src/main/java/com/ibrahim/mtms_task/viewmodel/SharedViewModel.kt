package com.ibrahim.mtms_task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel(): ViewModel() {

    var searchQueryLiveData = MutableLiveData<String>()
}