package com.gvoltr.placeshere.domain.di

import com.gvoltr.placeshere.domain.*
import org.koin.dsl.module

val domainModule = module {
    factory { PlaceCategoriesInteractor(get(), get()) }
    factory { PlacesByCategoryInteractor(get(), get()) }
    factory { AddressInteractor(get(), get()) }
    factory { LocationInteractor(get(), get()) }
    factory { PermissionInteractor(get()) }
}