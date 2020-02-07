package com.gvoltr.placeshere.presentation.places.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gvoltr.placeshere.data.entity.category.PlaceCategory
import com.gvoltr.placeshere.domain.*
import com.gvoltr.placeshere.presentation.utils.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlacesViewModel(
    private val placeCategoriesInteractor: PlaceCategoriesInteractor,
    private val placesByCategoryInteractor: PlacesByCategoryInteractor,
    private val addressInteractor: AddressInteractor,
    private val locationInteractor: LocationInteractor,
    private val permissionInteractor: PermissionInteractor
) : ViewModel() {

    private val subscriptions = CompositeDisposable()
    //Used to pass events to the view that require user interaction e.g. location request
    private val interactionLiveData = MutableLiveData<Event<InteractionEvent>>()

    init {
//        loadPlaceCategories()
//        getCurrentAddress()
//        categorySelected(PlaceCategory("landmark-attraction", "", ""))

        listenLocation()
    }

    override fun onCleared() {
        subscriptions.clear()
        locationInteractor.stopLocationUpdates()
        super.onCleared()
    }

    fun getInteractionEventsLiveData(): LiveData<Event<InteractionEvent>> {
        return interactionLiveData
    }

    fun locationAllowed() {
        listenLocation()
    }

    fun locationDisallowed() {
        requestLocation()
    }

    fun categorySelected(placeCategory: PlaceCategory) {
        subscriptions.add(
            placesByCategoryInteractor.loadPlacesForCategories(placeCategory.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                    },
                    {
                        it.printStackTrace()
                    })
        )
    }

    fun categoryUnselected(placeCategory: PlaceCategory) {

    }

    private fun listenLocation() {
        if (permissionInteractor.locationPermissionGranted()) {
            subscribeToLocationUpdates()
            locationInteractor.startLocationUpdates()
        } else {
            requestLocation()
        }
    }

    private fun subscribeToLocationUpdates() {
        subscriptions.add(locationInteractor.getLocationUpdates()
            .subscribe(
                {
                    Log.d("DDD", "new location in view model $it")
                    getCurrentAddress()
                    loadPlaceCategories()
                },
                {
                    it.printStackTrace()
                }
            )
        )
    }

    private fun requestLocation() {
        interactionLiveData.value = Event(InteractionEvent.RequestLocation)
    }

    private fun getCurrentAddress() {
        subscriptions.add(
            addressInteractor.getAddressOfCurrentLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("DDD", "Address loaded $it")
                    },
                    {
                        it.printStackTrace()
                    })
        )
    }

    private fun loadPlaceCategories() {
        subscriptions.add(
            placeCategoriesInteractor.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("DDD", "Place categories loaded $it")
                    },
                    {
                        it.printStackTrace()
                    })
        )
    }
}