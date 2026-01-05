package com.example.apiintegration.domain.model.Cart

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val quantity: Long,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double,
    val thumbnail: String,
)