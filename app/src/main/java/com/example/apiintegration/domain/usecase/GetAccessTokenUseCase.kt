//package com.example.apiintegration.domain.usecase
//
//import com.example.apiintegration.domain.repository.AuthRepository
//import com.example.apiintegration.domain.repository.ProfileLocalRepository
//import javax.inject.Inject
//
//class GetAccessTokenUseCase @Inject constructor(
//    private val profileLocalRepository: ProfileLocalRepository,
//) {
//    operator fun invoke(): String? {
//        return profileLocalRepository.getAccessTokenFromLocalStorage()
//    }
//}
//
//
