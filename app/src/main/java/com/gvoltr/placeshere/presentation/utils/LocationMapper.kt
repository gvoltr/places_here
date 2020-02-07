package com.gvoltr.placeshere.presentation.utils

import com.google.android.gms.maps.model.LatLng
import com.gvoltr.placeshere.data.entity.location.Location

fun Location.toLatLng() = LatLng(latitude, longitude)