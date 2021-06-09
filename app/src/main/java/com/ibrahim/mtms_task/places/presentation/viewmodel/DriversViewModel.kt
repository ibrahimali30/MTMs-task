package com.ibrahim.mtms_task.places.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.ibrahim.mtms_task.base.DRIVER_COLLECTION
import com.ibrahim.mtms_task.model.PlaceUiModel
import java.text.DecimalFormat
import javax.inject.Inject

class DriversViewModel @Inject constructor(): ViewModel() {

    fun getClosestDriver(sourceLocation: PlaceUiModel): MutableLiveData<String> {
        val closestDriverLiveData = MutableLiveData<String>()
        val db = FirebaseFirestore.getInstance()
        db.collection(DRIVER_COLLECTION)
                .addSnapshotListener { value, error ->
                    val response = value?.documents?.map {
                        it.toObject(PlaceUiModel::class.java)
                    }?.filterNotNull()

                    val closestDriver = response?.minByOrNull { it ->  CalculationByDistance(
                            LatLng(sourceLocation.lat,sourceLocation.lon),
                            LatLng(it.lat,it.lon)
                    ) }

                    closestDriver?.let {
                        val distance = CalculationByDistance(
                                LatLng(sourceLocation.lat, sourceLocation.lon),
                                LatLng(it.lat, it.lon))

                        closestDriverLiveData.value =
                                "The closest driver is ${it.name} at \n " +
                                "lat: ${it.lat} \n " +
                                "lon: ${it.lat} \n " +
                                "with distance ${distance.toInt()} KM"
                    }
                }

        return closestDriverLiveData
    }

    fun CalculationByDistance(StartP: LatLng, EndP: LatLng): Double {
        val Radius = 6371 // radius of earth in Km
        val lat1: Double = StartP.latitude
        val lat2: Double = EndP.latitude
        val lon1: Double = StartP.longitude
        val lon2: Double = EndP.longitude
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + (Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2)))
        val c = 2 * Math.asin(Math.sqrt(a))
        val valueResult = Radius * c
        val km = valueResult / 1
        val newFormat = DecimalFormat("####")
        val kmInDec: Int = Integer.valueOf(newFormat.format(km))
        val meter = valueResult % 1000
        val meterInDec: Int = Integer.valueOf(newFormat.format(meter))
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec)
        return Radius * c
    }


}