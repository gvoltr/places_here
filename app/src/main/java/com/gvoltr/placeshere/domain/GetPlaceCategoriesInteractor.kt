package com.gvoltr.placeshere.domain

import com.gvoltr.placeshere.data.entity.category.PlaceCategory
import com.gvoltr.placeshere.data.restapi.places.PlacesDataSource
import io.reactivex.Single

class GetPlaceCategoriesInteractor(private val placesDataSource: PlacesDataSource) {

    fun getCategories() : Single<List<PlaceCategory>> {
        return placesDataSource.getCategories("52.5161", "13.3780")
    }

}