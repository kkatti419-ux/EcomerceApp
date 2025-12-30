package com.example.apiintegration.domain.usecase

import com.example.apiintegration.domain.repository.AuthRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): String? {
        return repository.getAccessToken()
    }
}

