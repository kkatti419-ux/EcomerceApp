package com.example.apiintegration.domain.usecase.local_storage

import com.example.apiintegration.domain.repository.ProfileLocalRepository
import javax.inject.Inject

class StoreUserCredentialsLocallyUseCase @Inject constructor(
    private val profileLocalRepository: ProfileLocalRepository,
) {
    suspend operator fun invoke(
        username: String,
        firstname: String,
        lastname: String,
        phone: String,
        email: String,
        profileImage: String,
        gender: String,
    ) {
        profileLocalRepository.saveCredentialsToLocalStorage(
            username,
            firstname = firstname,
            lastname = lastname,
            phone = phone,
            email = email,
            profileImage = profileImage,
            gender = gender
        )
    }
}