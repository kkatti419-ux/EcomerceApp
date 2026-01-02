package com.example.apiintegration.domain.usecase.product

import com.example.apiintegration.domain.model.product.Product
import com.example.apiintegration.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Long): Result<Product> {
        return productRepository.getProductById(id)
    }
}


