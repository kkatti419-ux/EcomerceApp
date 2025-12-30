package com.example.apiintegration.domain.usecase

import com.example.apiintegration.domain.repository.AuthRepository
import javax.inject.Inject

data class SavedCredentials(
    val username: String?,
    val password: String?,
    val phone:String?
)

class GetSavedCredentialsUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): SavedCredentials {
        return SavedCredentials(
            username = repository.getSavedUsername(),
            password = repository.getSavedPassword(),
            phone = repository.getPhone()

        )
    }
}

