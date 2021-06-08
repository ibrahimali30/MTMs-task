package com.ibrahim.mtms_task.places.presentation.fragment

import com.ibrahim.mtms_task.model.PlaceUiModel

class DestinationSearchFragment: SearchFragment() {

    override fun getPlaces(query: String) {
        adapter.clear()
        viewModel.getDestinationLocations(query)
    }

    override fun onItemClicked(location: PlaceUiModel) {
        sharedViewModel.onDestinationClicked(location)
    }
}