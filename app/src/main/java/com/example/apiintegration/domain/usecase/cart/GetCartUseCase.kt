package com.example.apiintegration.domain.usecase.cart

import com.example.apiintegration.domain.repository.Cartrepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private  val repository: Cartrepository) {
    suspend operator fun invoke() = repository.getCarts()

}