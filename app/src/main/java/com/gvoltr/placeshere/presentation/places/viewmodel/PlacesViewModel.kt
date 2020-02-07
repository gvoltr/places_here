package com.gvoltr.placeshere.presentation.places.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gvoltr.placeshere.domain.LocationInteractor
import com.gvoltr.placeshere.domain.PlaceCategoriesInteractor
import com.gvoltr.placeshere.domain.PlacesByCategoryInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlacesViewModel(
    private val placeCategoriesInteractor: PlaceCategoriesInteractor,
    private val placesByCategoryInteractor: PlacesByCategoryInteractor,
    private val locationInteractor: LocationInteractor
) : ViewModel() {

    private val LOG_TAG = "PlacesViewModel"

    private val subscriptions = CompositeDisposable()
    private val placeCategoriesLivaData = MutableLiveData<List<PlaceCategoryItem>>()

    init {
        getCategoriesForLocation()
    }

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }

    fun getPlaceCategoriesLiveData(): LiveData<List<PlaceCategoryItem>> = placeCategoriesLivaData

    fun categorySelected(placeCategory: PlaceCategoryItem) {
        placeCategory.checked = true
        subscriptions.add(
            placesByCategoryInteractor.loadPlacesForCategories(placeCategory.placeCategory.id)
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

    fun categoryUnselected(placeCategory: PlaceCategoryItem) {
        placeCategory.checked = false

    }

    private fun getCategoriesForLocation() {
        //load place categories after we got first location update to be sure that location is available
        subscriptions.add(locationInteractor.getLocationUpdates()
            .firstOrError()
            .subscribe(
                {
                    Log.d("DDD", "new location in view model $it")
                    loadPlaceCategories()
                },
                {
                    Log.e(LOG_TAG, "getCategoriesForLocation error")
                    it.printStackTrace()
                }
            )
        )
    }

    private fun loadPlaceCategories() {
        subscriptions.add(
            placeCategoriesInteractor.getCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { categories ->
                        placeCategoriesLivaData.value = categories.map { PlaceCategoryItem(it) }
                    },
                    {
                        it.printStackTrace()
                    })
        )
    }
}