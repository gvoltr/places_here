package com.gvoltr.placeshere.data.restapi.address.parse

import com.google.gson.JsonSyntaxException
import org.junit.Assert
import org.junit.Test

class AddressParserTest {

    val parser = AddressParser()

    @Test
    fun `verify valid json parsing`() {
        val address = parser.parseAddress(validGetAddressResponse)
        Assert.assertEquals(validParsedAddress, address)
    }

    @Test(expected = JsonSyntaxException::class)
    fun `invalid json throw exception`() {
        parser.parseAddress("{ results: {")
    }

    @Test(expected = Exception::class)
    fun `invalid json structure throw exception`() {
        parser.parseAddress("{ Response: { notItems: []} }")
    }

}