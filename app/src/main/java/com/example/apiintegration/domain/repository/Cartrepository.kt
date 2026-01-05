package com.example.apiintegration.domain.repository

import com.example.apiintegration.domain.model.Cart.CartResponse
import javax.inject.Inject

interface Cartrepository  {
    suspend fun getCarts(): Result<CartResponse>
}

