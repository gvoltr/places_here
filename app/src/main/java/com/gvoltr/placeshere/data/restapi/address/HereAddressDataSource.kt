package com.gvoltr.placeshere.data.restapi.address

import com.gvoltr.placeshere.data.entity.address.Address
import com.gvoltr.placeshere.data.restapi.address.parse.AddressParser
import io.reactivex.Single

class HereAddressDataSource(
    private val apiKey: String,
    private val addressService: AddressService,
    private val addressParser: AddressParser
): AddressDataSource {

    override fun getAddressByLocation(latitude: String, longitude: String): Single<Address> {
        //API params are taken from documentation sample
        return addressService.getAddress(
            location = toHereLocation(latitude, longitude),
            mode = "retrieveAddress",
            maxResults = 1,
            gen = 9,
            apiKey = apiKey
        )
            .map { addressParser.parseAddress(it.string()) }
    }

    //TODO: clean this to be DRY
    private fun toHereLocation(latitude: String, longitude: String) = "$latitude,$longitude"

}