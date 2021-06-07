package com.ibrahim.mtms_task.places.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.mtms_task.R
import com.ibrahim.mtms_task.base.extensions.gone
import com.ibrahim.mtms_task.base.extensions.show
import com.ibrahim.mtms_task.model.PlaceUiModel
import com.ibrahim.mtms_task.places.presentation.viewmodel.PlacesViewModel
import com.ibrahim.mtms_task.places.presentation.view.MapsActivity
import com.ibrahim.mtms_task.places.presentation.adapter.SearchAdapter
import com.ibrahim.mtms_task.places.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.layout_top_views.*
import java.util.ArrayList
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment: Fragment() {

    @Inject
    lateinit var viewModel : PlacesViewModel

    val sharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    private lateinit var adapter: SearchAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container!!, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeScreenState()
        initSearchView()
        initRecyclerView()
        getPlaces("")
        (activity as MapsActivity).viewTopSpacing.gone()
    }

    private fun initSearchView() {
        sharedViewModel.searchQueryLiveData.observe(viewLifecycleOwner , Observer {
            getPlaces(it)
        })
    }

    private fun getPlaces(query: String) {
        adapter.clear()
        viewModel.getSourceLocations(query)
    }

    private fun initRecyclerView() {
        adapter = SearchAdapter(ArrayList(), ::onItemClicked)
        rvSearchResult.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvSearchResult.adapter = adapter
    }

    private fun onItemClicked(location: PlaceUiModel) {

    }


    private fun observeScreenState() {
        viewModel.screenState.observe(viewLifecycleOwner , Observer {
            onScreenStateChanged(it)
        })
    }


    private fun onScreenStateChanged(state: PlacesViewModel.ScreenState?) {
        when (state) {
            is PlacesViewModel.ScreenState.SuccessAPIResponse -> handleSuccess(state.data)
            is PlacesViewModel.ScreenState.ErrorLoadingFromApi -> handleErrorLoadingFromApi(state.error)
            else -> {}
        }

        handleLoadingVisibility(state == PlacesViewModel.ScreenState.Loading)
    }

    private fun handleErrorLoadingFromApi(error: Throwable) {

    }

    private fun handleLoadingVisibility(showLoading: Boolean) {
        progressBar.visibility = if (showLoading) View.VISIBLE else View.GONE
    }

    private fun handleSuccess(data: List<PlaceUiModel>) {
        adapter.setList(data)
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MapsActivity).viewTopSpacing.show()
    }

    
    
}

