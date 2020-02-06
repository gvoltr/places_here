package com.gvoltr.placeshere.data.restapi.places

import com.gvoltr.placeshere.data.entity.category.PlaceCategoriesResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesService {

    @GET("categories/places")
    fun getPlaceCategories(
        @Query("apiKey") apiKey: String,
        @Query("at") location: String
    ): Single<PlaceCategoriesResponse>


    @GET("discover/explore")
    fun getPlaces(
        @Query("at") location: String,
        @Query("apiKey") apiKey: String,
        @Query("cat") category: String
    ): Single<ResponseBody>

}