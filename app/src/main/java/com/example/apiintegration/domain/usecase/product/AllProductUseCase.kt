package com.example.apiintegration.domain.usecase.product

import com.example.apiintegration.domain.model.product.ProductResponse
import com.example.apiintegration.domain.repository.ProductRepository
import javax.inject.Inject

class AllProductUseCase @Inject constructor(
    val productRepository: ProductRepository,
) {
    suspend operator fun invoke(): Result<ProductResponse> {

        return productRepository.getProducts()
    }
}


