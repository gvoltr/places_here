package com.gvoltr.placeshere.data.entity.address

import com.gvoltr.placeshere.data.entity.location.Location

data class Address (
    val fullDescription: String,
    val displayPosition: Location
)