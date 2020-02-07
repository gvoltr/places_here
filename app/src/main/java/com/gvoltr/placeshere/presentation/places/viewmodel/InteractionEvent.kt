package com.gvoltr.placeshere.presentation.places.viewmodel

sealed class InteractionEvent {

    object RequestLocation: InteractionEvent()

}