package com.ben.weatherapp.Data.Location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.ben.weatherapp.Domain.Location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient:FusedLocationProviderClient,
    private val application: Application
): LocationTracker {

    override suspend fun getCurrentLocation(): Location? {
        val hasFineLocation = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasCoarseLocation = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if(!hasFineLocation || !hasCoarseLocation || !isGpsEnabled){
            return null
        }

        //we have a location permission

        return suspendCancellableCoroutine { cont -> //continuation
            locationClient.lastLocation.apply {
                //returns a task that we can use to check for successful result
                if(this.isComplete){
                    //continuation to continue with coroutine
                    if(this.isSuccessful){
                        cont.resume(this.result)
                    }
                    else{
                        // (this) task failed
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine

                }
                //Not complete yet
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnFailureListener {
                    cont.resume(null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}