package com.gvoltr.placeshere.domain.permission

import com.gvoltr.placeshere.data.permission.PermissionDataSource

class PermissionInteractor(private val permissionDataSource: PermissionDataSource) {

    fun locationPermissionGranted() = permissionDataSource.locationPermissionGranted()

}