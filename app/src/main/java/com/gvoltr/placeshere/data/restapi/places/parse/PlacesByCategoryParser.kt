package com.gvoltr.placeshere.data.restapi.places.parse

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
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
        var latitude = ""
        var longitude = ""
        (item.get("position") as JsonArray).let {
            latitude = it[0].asString
            longitude = it[1].asString
        }

        val title = item.get("title").asString ?: ""
        val icon = item.get("icon").asString ?: ""
        val vicinity = item.get("vicinity").asString ?: ""
        val detailsLink = item.get("href").asString ?: ""
        val category = item.get("category")?.asJsonObject?.get("title")?.asString ?: ""
        val categoryId = item.get("category")?.asJsonObject?.get("id")?.asString ?: ""

        return Place(
            latitude = latitude,
            longitude = longitude,
            title = title,
            icon = icon,
            vicinity = vicinity,
            detailsLink = detailsLink,
            categoryTitle = category,
            categoryId = categoryId
        )
    }
}