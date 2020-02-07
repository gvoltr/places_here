package com.gvoltr.placeshere.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gvoltr.placeshere.domain.LocationInteractor
import com.gvoltr.placeshere.domain.PermissionInteractor
import com.gvoltr.placeshere.presentation.utils.Event

class MainViewModel(
    private val locationInteractor: LocationInteractor,
    private val permissionInteractor: PermissionInteractor
) : ViewModel() {


    //Used to pass events to the view that require user interaction e.g. location request
    private val interactionLiveData = MutableLiveData<Event<InteractionEvent>>()

    init {
        initLocationUpdates()
    }

    override fun onCleared() {
        super.onCleared()
        locationInteractor.stopLocationUpdates()
    }

    fun getInteractionEventsLiveData(): LiveData<Event<InteractionEvent>> = interactionLiveData

    fun locationAllowed() {
        initLocationUpdates()
    }

    fun locationDisallowed() {
        requestLocation()
    }

    private fun initLocationUpdates() {
        if (permissionInteractor.locationPermissionGranted()) {
            locationInteractor.startLocationUpdates()
        } else {
            requestLocation()
        }
    }

    private fun requestLocation() {
        interactionLiveData.value = Event(InteractionEvent.RequestLocation)
    }

}