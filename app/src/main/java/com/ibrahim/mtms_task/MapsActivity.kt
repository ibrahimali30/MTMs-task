package com.ibrahim.mtms_task

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.layout_top_views.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

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
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


    }

}