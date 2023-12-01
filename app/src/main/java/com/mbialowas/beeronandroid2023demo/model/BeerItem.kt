package com.mbialowas.beeronandroid2023demo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "rating")
    val rating: Rating
) {
    // Empty constructor to initialize the Beer Item data class for firestore
    constructor() : this(0, "", "", "", Rating(0.00,0))
}