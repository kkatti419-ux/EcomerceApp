package com.example.apiintegration.data.repository

import com.example.apiintegration.data.remote.GeminiApi
import com.example.apiintegration.domain.model.product.Product
import com.example.apiintegration.domain.model.product.ProductResponse
import com.example.apiintegration.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    val geminiApi: GeminiApi
): ProductRepository {
    override suspend fun getProducts(): Result<ProductResponse> {
        return try {
            val response=geminiApi.getProducts();
            Result.success(response)
        }
        catch (e: Exception){
            Result.failure(e)

        }

    }

    override suspend fun getProductById(id: Long): Result<Product> {
        return try {
            val response = geminiApi.getProductById(id)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}