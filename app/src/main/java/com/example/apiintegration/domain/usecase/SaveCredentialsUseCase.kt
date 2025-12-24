package com.example.apiintegration.domain.usecase

import com.example.apiintegration.domain.repository.AuthRepository
import javax.inject.Inject

class SaveCredentialsUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String) {
        repository.saveCredentials(username, password)
    }
}
