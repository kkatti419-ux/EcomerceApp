package com.example.apiintegration.data.repository

import com.example.apiintegration.data.remote.api.ProductApi
import com.example.apiintegration.domain.model.product.Product
import com.example.apiintegration.domain.model.product.ProductResponse
import com.example.apiintegration.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    val productApi: ProductApi
): ProductRepository {
    override suspend fun getProducts(): Result<ProductResponse> {
        return try {
            val response=productApi.getProducts();
            Result.success(response)
        }
        catch (e: Exception){
            Result.failure(e)

        }

    }

    override suspend fun getProductById(id: Long): Result<Product> {
        return try {
            val response = productApi.getProductById(id)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}