package com.gvoltr.placeshere.presentation.places.viewmodel

import com.gvoltr.placeshere.data.entity.place.Place

data class PlaceItem(
    val place: Place,
    val categoryId: String
)