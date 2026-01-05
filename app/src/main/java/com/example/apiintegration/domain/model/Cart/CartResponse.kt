package com.example.apiintegration.domain.model.Cart

data class CartResponse(
    val carts: List<Cart>,
    val total: Long,
    val skip: Long,
    val limit: Long,
)