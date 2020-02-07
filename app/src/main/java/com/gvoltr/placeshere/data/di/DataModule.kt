package com.gvoltr.placeshere.data.di

import com.gvoltr.placeshere.data.location.FusedLocationDataSource
import com.gvoltr.placeshere.data.location.LocationDataSource
import com.gvoltr.placeshere.data.location.LocationManager
import com.gvoltr.placeshere.data.permission.AndroidPermissionsDataSource
import com.gvoltr.placeshere.data.permission.PermissionDataSource
import com.gvoltr.placeshere.data.restapi.address.AddressDataSource
import com.gvoltr.placeshere.data.restapi.address.AddressService
import com.gvoltr.placeshere.data.restapi.address.HereAddressDataSource
import com.gvoltr.placeshere.data.restapi.address.parse.AddressParser
import com.gvoltr.placeshere.data.restapi.places.HerePlacesDataSource
import com.gvoltr.placeshere.data.restapi.places.PlacesDataSource
import com.gvoltr.placeshere.data.restapi.places.PlacesService
import com.gvoltr.placeshere.data.restapi.places.parse.PlacesByCategoryParser
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


private const val HERE_TOKEN = "here_token"
private const val PLACES_BASE_URL = "places_base_url"
private const val ADDRESS_SERVICE_BASE_URL = "address_service_base_url"

private val apiModule = module {
    //TODO: secure token
    single(named(HERE_TOKEN)) { "87j_BbznObVueDqcXXyDel51B0A2-Z5Qkoyh8VTqN6A" }
    single(named(PLACES_BASE_URL)) { "https://places.ls.hereapi.com/places/v1/" }
    single(named(ADDRESS_SERVICE_BASE_URL)) {
        "https://reverse.geocoder.ls.hereapi.com/6.2/"
    }
    //Retrofit services
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(get<String>(named(PLACES_BASE_URL)))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(PlacesService::class.java)
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(get<String>(named(ADDRESS_SERVICE_BASE_URL)))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(AddressService::class.java)
    }
    //Parsers
    single { PlacesByCategoryParser() }
    single { AddressParser() }
    //Data Sources
    single<PlacesDataSource> {
        HerePlacesDataSource(get(named(HERE_TOKEN)), get(), get())
    }
    single<AddressDataSource> {
        HereAddressDataSource(get(named(HERE_TOKEN)), get(), get())
    }
}

private val locationModule = module {
    single { FusedLocationDataSource(get()) }
    factory<LocationDataSource> { get() as FusedLocationDataSource }
    factory<LocationManager> { get() as FusedLocationDataSource }
}

private val permissionModule = module {
    factory<PermissionDataSource> { AndroidPermissionsDataSource(get()) }
}

val dataModules = arrayOf(apiModule, locationModule, permissionModule)