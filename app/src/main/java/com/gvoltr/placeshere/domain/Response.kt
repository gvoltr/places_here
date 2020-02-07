package com.gvoltr.placeshere.domain

data class Response<T>(
    val value: T? = null,
    val error: Throwable? = null
)