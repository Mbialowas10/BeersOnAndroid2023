package com.mbialowas.beeronandroid2023demo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class BeerList(
    val beers: List<BeerItem>?=null
)
