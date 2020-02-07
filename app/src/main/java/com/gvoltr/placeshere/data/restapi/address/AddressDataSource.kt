package com.gvoltr.placeshere.data.restapi.address

import com.gvoltr.placeshere.data.entity.address.Address
import com.gvoltr.placeshere.data.entity.location.Location
import io.reactivex.Single

interface AddressDataSource {

    fun getAddressByLocation(location: Location) : Single<Address>

}