package com.gvoltr.placeshere.data.restapi

import com.gvoltr.placeshere.data.entity.location.Location

fun Location.toHereLocation() = "$latitude,$longitude"