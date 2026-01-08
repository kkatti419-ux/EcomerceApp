package com.example.apiintegration.domain.usecase

import com.example.apiintegration.domain.repository.ProfileLocalRepository
import javax.inject.Inject

class SavedCredentials(
    val username: String?,
    val password: String?,
    val phone:String?
)

class GetSavedCredentialsUseCase @Inject constructor(
    private val profileLocalRepository: ProfileLocalRepository
) {
    operator fun invoke(): SavedCredentials {
        return SavedCredentials(
            username = profileLocalRepository.getUsernameFromLocalStorage(),
            password = profileLocalRepository.getSavedPasswordFromLocalStorage(),
            phone = profileLocalRepository.getPhoneFromLocalStorage()
        )
    }
}


