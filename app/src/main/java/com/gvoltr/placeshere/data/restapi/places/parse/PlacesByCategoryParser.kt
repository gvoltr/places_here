package com.gvoltr.placeshere.data.restapi.places.parse

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.gvoltr.placeshere.data.entity.location.Location
import com.gvoltr.placeshere.data.entity.place.Place


/**
 * Parses response of places by category to list of Places
 */
class PlacesByCategoryParser {

    /**
     * Parse json string into list of places
     * If json string is not valid or structure is not matching expected this call will throw exception
     * @param json string representation of json to parse
     */
    fun toPlaceCategories(json: String): List<Place> {

        val jsonElement = JsonParser().parse(json)

        return jsonElement.asJsonObject
            .get("results").asJsonObject
            .get("items").asJsonArray
            .filter { it.isJsonObject }
            .map { parsePlaceInfo(it.asJsonObject) }
    }

    private fun parsePlaceInfo(item: JsonObject) : Place {
        var latitude: Double
        var longitude: Double
        (item.get("position") as JsonArray).let {
            latitude = it[0].asDouble
            longitude = it[1].asDouble
        }

        val title = item.get("title").asString ?: ""
        val icon = item.get("icon").asString ?: ""
        val vicinity = item.get("vicinity").asString ?: ""
        val detailsLink = item.get("href").asString ?: ""
        val category = item.get("category")?.asJsonObject?.get("title")?.asString ?: ""
        val categoryId = item.get("category")?.asJsonObject?.get("id")?.asString ?: ""

        return Place(
            title = title,
            icon = icon,
            vicinity = vicinity,
            detailsLink = detailsLink,
            categoryTitle = category,
            categoryId = categoryId,
            location = Location(latitude, longitude)
        )
    }
}