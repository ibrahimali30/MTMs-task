package com.ibrahim.mtms_task.places.presentation.view.fragment

import com.ibrahim.mtms_task.model.PlaceUiModel

class SourceSearchFragment: SearchFragment() {
    override fun getPlaces(query: String) {
        adapter.clear()
        viewModel.getSourceLocations(query)
    }

    override fun onItemClicked(location: PlaceUiModel) {
        sharedViewModel.onSourceItemClicked(location)
    }
}