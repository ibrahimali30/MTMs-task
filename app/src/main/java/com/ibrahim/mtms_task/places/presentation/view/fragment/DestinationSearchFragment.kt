package com.ibrahim.mtms_task.places.presentation.view.fragment

import com.ibrahim.mtms_task.model.PlaceUiModel

class DestinationSearchFragment: SearchFragment() {

    override fun getPlaces(query: String) {
        if (query.isEmpty()) return
        adapter.clear()
        viewModel.getDestinationLocations(query)
    }

    override fun onItemClicked(location: PlaceUiModel) {
        sharedViewModel.onDestinationClicked(location)
    }
}