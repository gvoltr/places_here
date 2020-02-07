package com.gvoltr.placeshere.presentation.places.viewmodel

import com.gvoltr.placeshere.data.entity.category.PlaceCategory

data class PlaceCategoryItem (
    val placeCategory: PlaceCategory,
    var checked: Boolean = false
)