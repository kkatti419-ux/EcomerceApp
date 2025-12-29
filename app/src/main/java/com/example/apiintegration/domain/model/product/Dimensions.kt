package com.example.apiintegration.domain.model.product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dimensions(
    val width: Double,
    val height: Double,
    val depth: Double
)
