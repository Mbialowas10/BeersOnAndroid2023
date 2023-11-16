package com.mbialowas.beeronandroid2023demo.api

import com.mbialowas.beeronandroid2023demo.model.BeerItem
import retrofit2.Call
import retrofit2.http.GET

interface BeersService {

    @GET("beers/ale")
    fun getBeers(): Call<List<BeerItem>>
}