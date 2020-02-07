package com.gvoltr.placeshere.domain.di

import com.gvoltr.placeshere.domain.address.AddressInteractor
import com.gvoltr.placeshere.domain.location.LocationInteractor
import com.gvoltr.placeshere.domain.permission.PermissionInteractor
import com.gvoltr.placeshere.domain.places.PlaceCategoriesInteractor
import com.gvoltr.placeshere.domain.places.PlacesByCategoryInteractor
import org.koin.dsl.module

val domainModule = module {
    factory {
        PlaceCategoriesInteractor(
            get(),
            get()
        )
    }
    factory {
        PlacesByCategoryInteractor(
            get(),
            get()
        )
    }
    factory {
        AddressInteractor(
            get(),
            get()
        )
    }
    factory {
        LocationInteractor(
            get(),
            get()
        )
    }
    factory { PermissionInteractor(get()) }
}