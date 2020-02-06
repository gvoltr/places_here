package com.gvoltr.placeshere.presentation.places.viewmodel

import androidx.lifecycle.ViewModel
import com.gvoltr.placeshere.data.entity.category.PlaceCategory
import com.gvoltr.placeshere.domain.GetAddressInteractor
import com.gvoltr.placeshere.domain.GetPlaceCategoriesInteractor
import com.gvoltr.placeshere.domain.GetPlacesByCategoryInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlacesViewModel(
    private val getPlaceCategoriesInteractor: GetPlaceCategoriesInteractor,
    private val getPlacesByCategoryInteractor: GetPlacesByCategoryInteractor,
    private val getAddressInteractor: GetAddressInteractor
) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    init {
        loadPlaceCategories()
        getCurrentAddress()
        categorySelected(PlaceCategory("landmark-attraction", "", ""))
    }

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }

    private fun loadPlaceCategories() {
        subscriptions.add(
            getPlaceCategoriesInteractor.getCategories()
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

    private fun categorySelected(placeCategory: PlaceCategory) {
        subscriptions.add(
            getPlacesByCategoryInteractor.loadPlacesForCategories(placeCategory.id)
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

    private fun getCurrentAddress() {
        subscriptions.add(
            getAddressInteractor.getAddressOfCurrentLocation()
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
}