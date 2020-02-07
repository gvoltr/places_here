package com.gvoltr.placeshere.domain.places

import com.gvoltr.placeshere.data.entity.place.Place
import com.gvoltr.placeshere.data.location.LocationDataSource
import com.gvoltr.placeshere.data.restapi.places.PlacesDataSource
import io.reactivex.Single

class PlacesByCategoryInteractor(
    private val placesDataSource: PlacesDataSource,
    private val locationDataSource: LocationDataSource
) {

    //TODO: implement in memory cache for already loaded places
    fun loadPlacesForCategories(categoryId: String): Single<List<Place>> {
        val location = locationDataSource.getLastKnownLocation()
            ?: return Single.error(IllegalStateException("Location required for places request"))
        return placesDataSource.getPlacesByCategory(categoryId, location)
    }
}