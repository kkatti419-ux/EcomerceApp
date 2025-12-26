package com.example.apiintegration.domain.model.product

data class ProductResponse(
    val products: List<Product>,
    val total: Long,
    val skip: Long,
    val limit: Long
)
