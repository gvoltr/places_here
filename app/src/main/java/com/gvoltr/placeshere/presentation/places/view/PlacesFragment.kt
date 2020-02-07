package com.gvoltr.placeshere.presentation.places.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gvoltr.placeshere.R
import com.gvoltr.placeshere.presentation.places.viewmodel.InteractionEvent
import com.gvoltr.placeshere.presentation.places.viewmodel.PlacesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlacesFragment : Fragment() {

    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private val placesViewModel: PlacesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                placesViewModel.locationAllowed()
            } else {
                placesViewModel.locationDisallowed()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToVMChanges()
    }

    private fun subscribeToVMChanges() {
        placesViewModel.getInteractionEventsLiveData()
            .observe(viewLifecycleOwner, Observer { event ->
                event.getContentIfNotHandled()?.let { handleInteractionEvent(it) }
            })
    }

    private fun handleInteractionEvent(event: InteractionEvent) {
        when (event) {
            is InteractionEvent.RequestLocation -> requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        fun requestPermission() {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity as Activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            AlertDialog.Builder(context!!)
                .setTitle(R.string.label_permission_required)
                .setMessage(R.string.location_permission_required_message)
                .setPositiveButton(R.string.ok) { _, _ ->
                    requestPermission()
                }
                .create()
                .show()
        } else {
            requestPermission()
        }
    }

}