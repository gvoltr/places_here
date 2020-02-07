package com.gvoltr.placeshere.domain

import com.gvoltr.placeshere.data.entity.category.PlaceCategory
import com.gvoltr.placeshere.data.location.LocationDataSource
import com.gvoltr.placeshere.data.restapi.places.PlacesDataSource
import io.reactivex.Single

class PlaceCategoriesInteractor(
    private val placesDataSource: PlacesDataSource,
    private val locationDataSource: LocationDataSource
) {

    fun getCategories(): Single<List<PlaceCategory>> {
        val location = locationDataSource.getLastKnownLocation()
            ?: return Single.error(IllegalStateException("Location required for categories request"))

        return placesDataSource.getCategories(location)
    }

}