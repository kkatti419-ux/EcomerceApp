package com.example.apiintegration.domain.model.product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductResponse(
    val products: List<Product>,
    val total: Long,
    val skip: Long,
    val limit: Long
)
