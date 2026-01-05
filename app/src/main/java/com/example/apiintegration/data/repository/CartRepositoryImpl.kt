package com.example.apiintegration.data.repository

import com.example.apiintegration.data.remote.GeminiApi
import com.example.apiintegration.domain.model.Cart.CartResponse
import com.example.apiintegration.domain.repository.Cartrepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val api: GeminiApi,
) : Cartrepository {
    override suspend fun getCarts(): Result<CartResponse> {
        return try {
            val response = api.getCarts()
            return Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}