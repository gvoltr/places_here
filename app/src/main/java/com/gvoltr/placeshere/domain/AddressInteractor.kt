package com.gvoltr.placeshere.domain

import com.gvoltr.placeshere.data.entity.address.Address
import com.gvoltr.placeshere.data.location.LocationDataSource
import com.gvoltr.placeshere.data.restapi.address.AddressDataSource
import io.reactivex.Single

class AddressInteractor(
    private val addressDataSource: AddressDataSource,
    private val locationDataSource: LocationDataSource
) {

    fun getAddressOfCurrentLocation(): Single<Address> {
        val location = locationDataSource.getLastKnownLocation()
            ?: return Single.error(IllegalStateException("Location required for address request"))

        return addressDataSource.getAddressByLocation(location)
    }

}