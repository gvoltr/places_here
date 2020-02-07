package com.gvoltr.placeshere.data.location

import com.gvoltr.placeshere.data.entity.location.Location
import io.reactivex.Observable

interface LocationDataSource {

    fun getLastKnownLocation() : Location?

    fun getLocationStream() : Observable<Location>

}