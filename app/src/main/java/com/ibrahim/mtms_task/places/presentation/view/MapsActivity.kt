package com.ibrahim.mtms_task.places.presentation.view

import android.Manifest
import android.annotation.SuppressLint
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
import com.ibrahim.mtms_task.base.extensions.gone
import com.ibrahim.mtms_task.base.extensions.show
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

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() {

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

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0){
                viewTopSpacing.gone()
                btMenuToggle.setIconResource(R.drawable.ic_baseline_arrow_back_24)
                btMenuToggle.setOnClickListener {
                    supportFragmentManager.popBackStack()
                }
            }else{
                viewTopSpacing.show()
                btMenuToggle.setIconResource(R.drawable.ic_baseline_menu_24)
                btMenuToggle.setOnClickListener {
                    drawer_layout.openDrawer(Gravity.LEFT)
                }
            }
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