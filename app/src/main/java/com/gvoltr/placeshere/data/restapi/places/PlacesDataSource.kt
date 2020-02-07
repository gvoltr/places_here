package com.gvoltr.placeshere.data.restapi.places

import com.gvoltr.placeshere.data.entity.category.PlaceCategory
import com.gvoltr.placeshere.data.entity.location.Location
import com.gvoltr.placeshere.data.entity.place.Place
import io.reactivex.Single

interface PlacesDataSource {

    fun getCategories(
        location: Location
    ): Single<List<PlaceCategory>>

    fun getPlacesByCategory(
        categoryId: String,
        location: Location
    ): Single<List<Place>>

}