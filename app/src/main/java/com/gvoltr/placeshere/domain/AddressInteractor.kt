package com.gvoltr.placeshere.domain

import com.gvoltr.placeshere.data.entity.address.Address
import com.gvoltr.placeshere.data.location.LocationDataSource
import com.gvoltr.placeshere.data.restapi.address.AddressDataSource
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class AddressInteractor(
    private val addressDataSource: AddressDataSource,
    private val locationDataSource: LocationDataSource
) {

    fun getAddressChangesStream(): Observable<Address> {
        return locationDataSource.getLocationStream()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap { addressDataSource.getAddressByLocation(it).toObservable() }
    }
}