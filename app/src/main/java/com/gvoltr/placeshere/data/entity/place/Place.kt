package com.gvoltr.placeshere.data.entity.place

import android.text.Html
import com.gvoltr.placeshere.data.entity.location.Location

data class Place(
    val title: String,
    val icon: String,
    val vicinity: String,
    val detailsLink: String,
    val categoryTitle: String,
    val categoryId: String,
    val location: Location
) {
    fun fullDescription(): String {
        return "$title\n" +
                "$categoryTitle\n" +
                "${Html.fromHtml(vicinity)}"
    }
}