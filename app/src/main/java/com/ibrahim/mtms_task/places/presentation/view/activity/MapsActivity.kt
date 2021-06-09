package com.ibrahim.mtms_task.places.presentation.view.activity

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.ibrahim.mtms_task.R
import com.ibrahim.mtms_task.UserLocationManager
import com.ibrahim.mtms_task.base.extensions.hideKeyboard
import com.ibrahim.mtms_task.base.extensions.setActivityFullScreen
import com.ibrahim.mtms_task.base.extensions.setViewMargin
import com.ibrahim.mtms_task.base.extensions.showAlertDialog
import com.ibrahim.mtms_task.model.PlaceUiModel
import com.ibrahim.mtms_task.places.presentation.view.fragment.DestinationSearchFragment
import com.ibrahim.mtms_task.places.presentation.view.fragment.SourceSearchFragment
import com.ibrahim.mtms_task.places.presentation.viewmodel.DriversViewModel
import com.ibrahim.mtms_task.places.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.layout_top_views.*


@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {


    var sourceLocation: PlaceUiModel? = null
    var destinationLocation: PlaceUiModel? = null

    private lateinit var mMap: GoogleMap
    val sharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    val driversViewModel by lazy {
        ViewModelProvider(this).get(DriversViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        initMap()
        initViews()
        initObservers()
        setActivityFullScreen()
    }

    private fun initObservers() {
        sharedViewModel.selectedDestinationLiveData.observe(this, Observer {
            destinationLocation = it
            etDestinationLocation.setText(it.name)
        })

        sharedViewModel.selectedSourceLiveData.observe(this, Observer {
            sourceLocation = it
            etSourceLocation.setText(it.name)
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() {

        btRequest.setOnClickListener {
            if (sourceLocation != null && destinationLocation != null) {
                getClosestDriver()
            }else{
                showAlertDialog("", "Select source and destination location")
            }
        }

        etSourceLocation.setOnTouchListener { v, event ->
            showSearchFragment(SourceSearchFragment())
            return@setOnTouchListener false
        }
        etDestinationLocation.setOnTouchListener { v, event ->
            showSearchFragment(DestinationSearchFragment())
            return@setOnTouchListener false
        }

        etSourceLocation.doAfterTextChanged {
            sharedViewModel.onQueryTextChanged(it.toString())
        }

        etDestinationLocation.doAfterTextChanged {
            sharedViewModel.onQueryTextChanged(it.toString())
        }

        handleViewsVisibility()
        supportFragmentManager.addOnBackStackChangedListener {
            handleViewsVisibility()
        }
    }

    private fun getClosestDriver() {
        driversViewModel.getClosestDriver(sourceLocation!!).observe(this, Observer {
            showAlertDialog("Driver", it)
        })
        hideSearchViews()
    }

    private fun hideSearchViews() {
        hideKeyboard()
        etSourceLocation.clearFocus()
        etDestinationLocation.clearFocus()
        supportFragmentManager.popBackStack()
    }

    private fun handleViewsVisibility() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            materialCardView.setViewMargin(this, 0, 30, 0, 0)
            btMenuToggle.setIconResource(R.drawable.ic_baseline_arrow_back_24)
            btMenuToggle.setOnClickListener {
                hideSearchViews()
            }
        } else {
            materialCardView.setViewMargin(this, 16, 60, 16, 16)
            btMenuToggle.setIconResource(R.drawable.ic_baseline_menu_24)
            btMenuToggle.setOnClickListener {
                drawer_layout.openDrawer(Gravity.LEFT)
            }
        }
    }

    private fun showSearchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
                .replace(R.id.flSearchResult, fragment)
                .addToBackStack("")
                .commit()
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        locationManager.askForPermission()
        val screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels
        mMap.setPadding(0, screenHeight - 100, 0, 0)

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(30.0,31.3), 10f));
    }


    @SuppressLint("MissingPermission")
    private val locationManager = UserLocationManager(this, {
        mMap.isMyLocationEnabled = true
    }, {
        Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
    })

}