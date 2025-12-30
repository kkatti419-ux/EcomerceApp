package com.example.apiintegration.domain.usecase

import com.example.apiintegration.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(accessToken: String, refreshToken: String) {
        repository.saveToken(accessToken, refreshToken)
    }
}

