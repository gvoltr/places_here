package com.gvoltr.placeshere.presentation.places.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gvoltr.placeshere.data.entity.category.PlaceCategory
import com.gvoltr.placeshere.data.entity.location.Location
import com.gvoltr.placeshere.data.entity.place.Place
import com.gvoltr.placeshere.domain.location.LocationInteractor
import com.gvoltr.placeshere.domain.places.PlaceCategoriesInteractor
import com.gvoltr.placeshere.domain.places.PlacesByCategoryInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PlacesViewModel(
    private val placeCategoriesInteractor: PlaceCategoriesInteractor,
    private val placesByCategoryInteractor: PlacesByCategoryInteractor,
    private val locationInteractor: LocationInteractor
) : ViewModel() {

    private val LOG_TAG = "PlacesViewModel"

    private val subscriptions = CompositeDisposable()
    private val placeCategoriesLivaData = MutableLiveData<List<PlaceCategoryItem>>()
    private val placesLiveData = MutableLiveData<List<Place>>()

    //one time location event for pointing map to the right place
    private val currentLocationLiveData = MutableLiveData<Location>()

    //Define what should be shown map or list
    private val viewModeLiveData = MutableLiveData<ViewMode>()

    //In memory cache for loaded places. Used for easy places deletion by category
    private val loadedPlaces = HashMap<String, List<Place>>()

    init {
        listenToLocationChanges()
    }

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }

    fun getPlaceCategoriesLiveData(): LiveData<List<PlaceCategoryItem>> = placeCategoriesLivaData

    fun getPlacesLiveData(): LiveData<List<Place>> = placesLiveData

    fun getCurrentLocationLiveData(): LiveData<Location> = currentLocationLiveData

    fun getViewModeLiveData(): LiveData<ViewMode> = viewModeLiveData

    fun categorySelected(placeCategoryItem: PlaceCategoryItem) {
        placeCategoryItem.checked = true
        loadCategory(placeCategoryItem.placeCategory)
    }

    fun categoryUnselected(placeCategoryItem: PlaceCategoryItem) {
        placeCategoryItem.checked = false
        removePlacesOfCategory(placeCategoryItem.placeCategory)
    }

    fun mapModeSelected() {
        viewModeLiveData.value = ViewMode.MapMode
    }

    fun listModeSelected() {
        viewModeLiveData.value = ViewMode.ListMode
    }

    private fun listenToLocationChanges() {
        subscriptions.add(
            locationInteractor.getLocationUpdates()
                .throttleLatest(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        currentLocationLiveData.value = it
                        loadPlaceCategories()
                    },
                    {
                        it.printStackTrace()
                    })
        )
    }

    private fun loadPlaceCategories() {
        // load only once
        if (placeCategoriesLivaData.value.isNullOrEmpty()) {
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

    private fun loadCategory(category: PlaceCategory) {
        subscriptions.add(
            placesByCategoryInteractor.loadPlacesForCategories(category.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { newPlaces ->
                        addPlaces(newPlaces, category)
                    },
                    {
                        it.printStackTrace()
                    })
        )
    }

    private fun addPlaces(newPlaces: List<Place>, category: PlaceCategory) {
        loadedPlaces[category.id] = newPlaces
        displayPlaces()
    }

    private fun removePlacesOfCategory(category: PlaceCategory) {
        loadedPlaces.remove(category.id)
        displayPlaces()
    }

    private fun displayPlaces() {
        placesLiveData.value = loadedPlaces.values.flatten().sortedBy { it.title }.toList()
    }
}