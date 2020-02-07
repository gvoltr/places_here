package com.gvoltr.placeshere.presentation.main.viewmodel

sealed class InteractionEvent {

    object RequestLocation: InteractionEvent()

}