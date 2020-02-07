package com.gvoltr.placeshere.data.location

import android.content.Context
import android.util.Log
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.gvoltr.placeshere.data.entity.location.Location
import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject

class FusedLocationDataSource(
    context: Context
) : LocationDataSource, LocationManager {
    private val LOG_TAG = "LocationDataSource"

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private var locationRequest: LocationRequest = LocationRequest().apply {
        interval = 30000 //10s
        fastestInterval = 5000 //5s
        priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    }

    private var lastKnownLocation: Location? = null
    private val locationStream = ReplaySubject.create<Location>()

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            handleFreshLocationResult(locationResult)
        }
    }

    private var locationUpdatesRunning = false

    override fun getLastKnownLocation(): Location? = lastKnownLocation

    override fun getLocationStream(): Observable<Location> = locationStream

    override fun startLocationUpdates() {
        if (!locationUpdatesRunning) {
            locationUpdatesRunning = true
            Log.d(LOG_TAG, "starting location updates")
            fusedLocationClient?.requestLocationUpdates(locationRequest, locationCallback, null)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    lastKnownLocation = location.toAppLocation()
                    lastKnownLocation?.let { locationStream.onNext(it) }
                }
        }
    }

    override fun stopLocationUpdates() {
        if (locationUpdatesRunning) {
            locationUpdatesRunning = false
            Log.d(LOG_TAG, "stopping location updates")
            fusedLocationClient?.removeLocationUpdates(locationCallback)
        }
    }

    private fun handleFreshLocationResult(locationResult: LocationResult?) {
        Log.d(LOG_TAG, "Location updated ${locationResult?.locations}")
        locationResult ?: return
        lastKnownLocation = locationResult.locations.last().toAppLocation()
        lastKnownLocation?.let { locationStream.onNext(it) }
    }

    private fun android.location.Location.toAppLocation() = Location(latitude, longitude)

}