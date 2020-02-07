package com.gvoltr.placeshere.presentation.main.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.gvoltr.placeshere.R
import com.gvoltr.placeshere.presentation.main.viewmodel.InteractionEvent
import com.gvoltr.placeshere.presentation.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeToVM()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                mainViewModel.locationAllowed()
            } else {
                mainViewModel.locationDisallowed()
            }
        }
    }

    private fun subscribeToVM() {
        mainViewModel.getInteractionEventsLiveData()
            .observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { handleInteractionEvent(it) }
            })
    }

    private fun handleInteractionEvent(event: InteractionEvent) {
        when (event) {
            is InteractionEvent.RequestLocation -> askToEnableLocationPermission()
        }
    }

    private fun askToEnableLocationPermission() {
        val shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (shouldShowRationale) {
            AlertDialog.Builder(this)
                .setTitle(R.string.label_permission_required)
                .setMessage(R.string.location_permission_required_message)
                .setPositiveButton(R.string.ok) { _, _ ->
                    requestLocationPermission()
                }
                .create()
                .show()
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }
}
