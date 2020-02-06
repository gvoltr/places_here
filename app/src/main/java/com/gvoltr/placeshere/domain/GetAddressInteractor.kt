package com.gvoltr.placeshere.domain

import com.gvoltr.placeshere.data.entity.address.Address
import com.gvoltr.placeshere.data.restapi.address.AddressDataSource
import io.reactivex.Single

class GetAddressInteractor (private val addressDataSource: AddressDataSource) {

    fun getAddressOfCurrentLocation() : Single<Address> {
        return addressDataSource.getAddressByLocation("52.5161", "13.3780")
    }

}