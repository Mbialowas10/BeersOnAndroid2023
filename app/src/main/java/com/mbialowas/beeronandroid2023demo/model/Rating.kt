package com.mbialowas.beeronandroid2023demo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rating(
    @Json(name = "average")
    val average: Double,
    @Json(name = "reviews")
    val reviews: Int
) {
    // Empty constructor to initialize the Beer Item data class for firestore
    constructor() : this(0.00,0)
}