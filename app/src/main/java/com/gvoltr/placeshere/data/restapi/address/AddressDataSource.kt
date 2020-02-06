package com.gvoltr.placeshere.data.restapi.address

import com.gvoltr.placeshere.data.entity.address.Address
import io.reactivex.Single

interface AddressDataSource {

    fun getAddressByLocation(latitude: String, longitude: String) : Single<Address>

}