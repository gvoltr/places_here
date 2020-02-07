package com.gvoltr.placeshere.data.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class AndroidPermissionsDataSource(private val context: Context) : PermissionDataSource {

    override fun locationPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

}