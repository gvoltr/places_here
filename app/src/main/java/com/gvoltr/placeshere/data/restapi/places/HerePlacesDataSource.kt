package com.gvoltr.placeshere.data.restapi.places

import com.gvoltr.placeshere.data.entity.category.PlaceCategory
import com.gvoltr.placeshere.data.entity.location.Location
import com.gvoltr.placeshere.data.entity.place.Place
import com.gvoltr.placeshere.data.restapi.places.parse.PlacesByCategoryParser
import com.gvoltr.placeshere.data.restapi.toHereLocation
import io.reactivex.Single

class HerePlacesDataSource(
    private val apiKey: String,
    private val placesService: PlacesService,
    private val placesByCategoryParser: PlacesByCategoryParser
) : PlacesDataSource {

    override fun getCategories(
        location: Location
    ): Single<List<PlaceCategory>> {
        return placesService.getPlaceCategories(
            apiKey = apiKey,
            location = location.toHereLocation()
        ).map { it.items }
    }

    override fun getPlacesByCategory(
        categoryId: String,
        location: Location
    ): Single<List<Place>> {
        return placesService.getPlaces(
            apiKey = apiKey,
            location = location.toHereLocation(),
            category = categoryId
        ).map { placesByCategoryParser.toPlaceCategories(it.string()) }
    }
}