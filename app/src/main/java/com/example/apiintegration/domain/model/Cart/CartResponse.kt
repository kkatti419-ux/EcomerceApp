package com.example.apiintegration.domain.model.Cart

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartResponse(
    val carts: List<Cart>,
    val total: Long,
    val skip: Long,
    val limit: Long,
)