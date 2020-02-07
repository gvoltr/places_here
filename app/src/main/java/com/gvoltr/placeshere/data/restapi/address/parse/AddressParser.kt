package com.gvoltr.placeshere.data.restapi.address.parse

import com.google.gson.JsonParser
import com.gvoltr.placeshere.data.entity.address.Address
import com.gvoltr.placeshere.data.entity.location.Location

/**
 * Parses response of "Reverse Geocode an Address from a Location" to Address
 * @param json string representation of json to parse
 */
class AddressParser {

    /**
     * Parse json string into Address
     * If json string is not valid or structure is not matching expected this call will throw exception
     * @param json string representation of json to parse
     */
    fun parseAddress(json: String): Address {
        val jsonElement = JsonParser().parse(json)

        val location = jsonElement.asJsonObject
            .get("Response").asJsonObject
            .get("View").asJsonArray
            .first().asJsonObject
            .get("Result").asJsonArray
            .first().asJsonObject
            .get("Location").asJsonObject

        val fullDescription = location
            .get("Address").asJsonObject
            .get("Label").asString

        val displayPosition = location.get("DisplayPosition").asJsonObject

        val latitude = displayPosition.get("Latitude").asDouble
        val longitude = displayPosition.get("Longitude").asDouble

        return Address(
            fullDescription = fullDescription,
            displayPosition = Location(latitude, longitude)
        )
    }
}