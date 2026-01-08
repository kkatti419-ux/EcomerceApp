package com.example.apiintegration.domain.usecase.local_storage

import com.example.apiintegration.domain.model.LocalStorage.LocalUserCredentials
import com.example.apiintegration.domain.repository.ProfileLocalRepository
import javax.inject.Inject

class GetLocalUserCredentialsUseCase @Inject constructor(private val profileLocalRepository: ProfileLocalRepository){
    operator fun invoke(): LocalUserCredentials {
        return profileLocalRepository.getLocalUserCredentials()
    }
}