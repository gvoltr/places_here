package com.gvoltr.placeshere.domain

import com.gvoltr.placeshere.data.permission.PermissionDataSource

class PermissionInteractor(private val permissionDataSource: PermissionDataSource) {

    fun locationPermissionGranted() = permissionDataSource.locationPermissionGranted()

}