package com.gvoltr.placeshere.presentation.di

import com.gvoltr.placeshere.presentation.address.viewmodel.AddressViewModel
import com.gvoltr.placeshere.presentation.places.viewmodel.PlacesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelsModule = module {
    viewModel { PlacesViewModel(get(), get(), get(), get()) }
    viewModel { AddressViewModel(get()) }
}

val presentationModule = viewModelsModule