package com.gvoltr.placeshere.domain.di

import com.gvoltr.placeshere.domain.GetAddressInteractor
import com.gvoltr.placeshere.domain.GetPlaceCategoriesInteractor
import com.gvoltr.placeshere.domain.GetPlacesByCategoryInteractor
import org.koin.dsl.module

val domainModule = module {
    factory { GetPlaceCategoriesInteractor(get()) }
    factory { GetPlacesByCategoryInteractor(get()) }
    factory { GetAddressInteractor(get()) }
}