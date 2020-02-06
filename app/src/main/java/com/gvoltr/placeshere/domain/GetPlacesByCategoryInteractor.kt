package com.gvoltr.placeshere.domain

import com.gvoltr.placeshere.data.entity.place.Place
import com.gvoltr.placeshere.data.restapi.places.PlacesDataSource
import io.reactivex.Single

class GetPlacesByCategoryInteractor(private val placesDataSource: PlacesDataSource) {

    //TODO: implement in memory cache for already loaded places
    fun loadPlacesForCategories(categoryId: String): Single<List<Place>> {
        return placesDataSource.getPlacesByCategory(categoryId, "52.5161", "13.3780")
    }
}