package com.example.apiintegration.domain.repository

import com.example.apiintegration.domain.model.Post
import com.example.apiintegration.domain.model.product.Product
import com.example.apiintegration.domain.model.product.ProductResponse

interface ProductRepository {
    suspend fun getProducts(): Result<ProductResponse>
    suspend fun getProductById(id: Long): Result<Product>
}