package com.ibrahim.mtms_task.places.presentation.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.ibrahim.mtms_task.R
import com.ibrahim.mtms_task.places.presentation.fragment.SearchFragment
import com.ibrahim.mtms_task.places.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.layout_top_views.*

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val sharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initViews()
    }

    private fun initViews() {
        btMenuToggle.setOnClickListener {
            drawer_layout.openDrawer(Gravity.LEFT)
        }

        etSourceLocation.setOnTouchListener { v, event ->
            showSearchFragment()
            return@setOnTouchListener false
        }
            etDestinationLocation.setOnTouchListener { v, event ->
            showSearchFragment()
                return@setOnTouchListener false
        }

        etSourceLocation.doAfterTextChanged {
            sharedViewModel.onQueryTextChanged(it.toString())
        }

        etDestinationLocation.doAfterTextChanged {
            sharedViewModel.onQueryTextChanged(it.toString())
        }


    }

    private fun showSearchFragment() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
                .replace(R.id.flSearchResult, SearchFragment())
                .addToBackStack("")
                .commit()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true
    }

}