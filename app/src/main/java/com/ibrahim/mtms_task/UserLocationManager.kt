package com.ibrahim.mtms_task

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Handler
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class UserLocationManager(
    val activity: AppCompatActivity,
    val onLocationGranted: (location: Location) -> Unit,
    val onPermissionDenied: () -> Unit,
) {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(activity)
    }


    private val requestPermissionLauncher=
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getLasKnownLocation()
            } else {
                onPermissionDenied()
            }
        }


    fun askForPermission() {
        val permissionStatus = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)

        if(permissionStatus == PackageManager.PERMISSION_GRANTED ) {
            getLasKnownLocation()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLasKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener(activity) { location ->
                if (location != null) {
                    onLocationGranted(location)
                }else{
                    getLasKnownLocation()
                }
            }
    }

}