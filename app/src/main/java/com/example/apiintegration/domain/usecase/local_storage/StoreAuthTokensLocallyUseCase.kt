package com.example.apiintegration.domain.usecase.local_storage

import com.example.apiintegration.domain.repository.ProfileLocalRepository
import javax.inject.Inject

class StoreAuthTokensLocallyUseCase @Inject constructor(
    private val profileLocalRepository: ProfileLocalRepository
) {
    suspend operator fun invoke(accessToken: String, refreshToken: String) {
        profileLocalRepository.saveTokenToLocalStorage(accessToken, refreshToken)
    }
}