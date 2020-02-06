package com.gvoltr.placeshere.data.restapi.places

import com.gvoltr.placeshere.data.entity.category.PlaceCategory
import com.gvoltr.placeshere.data.entity.place.Place
import com.gvoltr.placeshere.data.restapi.places.parse.PlacesByCategoryParser
import io.reactivex.Single

class HerePlacesDataSource(
    private val apiKey: String,
    private val placesService: PlacesService,
    private val placesByCategoryParser: PlacesByCategoryParser
) : PlacesDataSource {

    override fun getCategories(
        latitude: String,
        longitude: String
    ): Single<List<PlaceCategory>> {
        return placesService.getPlaceCategories(
            apiKey = apiKey,
            location = toHereLocation(latitude, longitude)
        )
            .map { it.items }
    }

    override fun getPlacesByCategory(
        categoryId: String,
        latitude: String,
        longitude: String
    ): Single<List<Place>> {
        return placesService.getPlaces(
            apiKey = apiKey,
            location = toHereLocation(latitude, longitude),
            category = categoryId
        )
            .map { placesByCategoryParser.toPlaceCategories(it.string()) }
    }

    //TODO: clean this to be DRY
    private fun toHereLocation(latitude: String, longitude: String) = "$latitude,$longitude"

}