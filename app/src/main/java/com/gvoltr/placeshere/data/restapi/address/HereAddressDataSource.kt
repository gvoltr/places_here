package com.gvoltr.placeshere.data.restapi.address

import com.gvoltr.placeshere.data.entity.address.Address
import com.gvoltr.placeshere.data.entity.location.Location
import com.gvoltr.placeshere.data.restapi.address.parse.AddressParser
import com.gvoltr.placeshere.data.restapi.toHereLocation
import io.reactivex.Single

class HereAddressDataSource(
    private val apiKey: String,
    private val addressService: AddressService,
    private val addressParser: AddressParser
) : AddressDataSource {

    override fun getAddressByLocation(location: Location): Single<Address> {
        //API params are taken from documentation sample
        return addressService.getAddress(
            location = location.toHereLocation(),
            mode = "retrieveAddress",
            maxResults = 1,
            gen = 9,
            apiKey = apiKey
        ).map { addressParser.parseAddress(it.string()) }
    }
}