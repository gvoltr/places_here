package com.gvoltr.placeshere.presentation.places.viewmodel

sealed class ViewMode {

    object MapMode: ViewMode()

    object ListMode: ViewMode()

}