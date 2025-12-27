package com.example.apiintegration.domain.repository

import com.example.apiintegration.domain.model.Post
import com.example.apiintegration.domain.model.product.ProductResponse

interface ProductRepository {
    suspend fun getProducts(): Result<ProductResponse>
}