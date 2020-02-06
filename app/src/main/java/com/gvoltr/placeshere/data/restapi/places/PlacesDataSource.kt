package com.gvoltr.placeshere.data.restapi.places

import com.gvoltr.placeshere.data.entity.category.PlaceCategory
import com.gvoltr.placeshere.data.entity.place.Place
import io.reactivex.Single

interface PlacesDataSource {

    fun getCategories(
        latitude: String,
        longitude: String
    ): Single<List<PlaceCategory>>

    fun getPlacesByCategory(
        categoryId: String,
        latitude: String,
        longitude: String
    ): Single<List<Place>>

}