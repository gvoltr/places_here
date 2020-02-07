package com.gvoltr.placeshere.domain

import com.gvoltr.placeshere.data.location.LocationDataSource
import com.gvoltr.placeshere.data.location.LocationManager

class LocationInteractor(
    private val locationDataSource: LocationDataSource,
    private val locationManager: LocationManager
) {

    fun startLocationUpdates() {
        locationManager.startLocationUpdates()
    }

    fun stopLocationUpdates() {
        locationManager.stopLocationUpdates()
    }

    fun getLocationUpdates() = locationDataSource.getLocationStream()

}