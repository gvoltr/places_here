package com.gvoltr.placeshere.data.restapi.address

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressService {

    @GET("reversegeocode.json")
    fun getAddress(
        @Query("prox") location: String,
        @Query("mode") mode: String,
        @Query("maxresults") maxResults: Int,
        @Query("gen") gen: Int,
        @Query("apiKey") apiKey: String
    ) : Single<ResponseBody>

}