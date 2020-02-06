package com.gvoltr.placeshere.data.restapi.places.parse

import com.google.gson.JsonSyntaxException
import org.junit.Assert
import org.junit.Test

class PlacesByCategoryParserTest {

    private val parser = PlacesByCategoryParser()

    @Test
    fun `verify valid json parsing`() {
        val places = parser.toPlaceCategories(validGetPlacesJson)
        Assert.assertEquals(2, places.size)
        Assert.assertTrue(places.containsAll(validParsedPlaces))
    }

    @Test(expected = JsonSyntaxException::class)
    fun `invalid json throw exception`() {
        parser.toPlaceCategories("{ results: {")
    }

    @Test(expected = Exception::class)
    fun `invalid json structure throw exception`() {
        parser.toPlaceCategories("{ results: { notItems: []} }")
    }

    @Test
    fun `empty items returns empty list`() {
        val places = parser.toPlaceCategories("{ results: { items: []} }")
        Assert.assertTrue(places.isEmpty())
    }

}