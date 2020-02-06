package com.gvoltr.placeshere.data.entity.place

data class Place (
    val longitude: String,
    val latitude: String,
    val title: String,
    val icon: String,
    val vicinity: String,
    val detailsLink: String,
    val categoryTitle: String,
    val categoryId: String
)