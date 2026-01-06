package com.example.apiintegration.domain.model.Cart

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cart(
    val id: Long,
    val products: List<Product>,
    val total: Double,
    val discountedTotal: Double,
    val userId: Long,
    val totalProducts: Long,
    val totalQuantity: Long,
)