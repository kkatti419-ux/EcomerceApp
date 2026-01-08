package com.example.apiintegration.domain.usecase.local_user

import com.example.apiintegration.domain.model.UserData.UserDetails
import com.example.apiintegration.domain.repository.UserDetailReposirotory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDetailsUseCase @Inject constructor(private val userDetailRepository: UserDetailReposirotory) {
    suspend fun upsertData(userData: UserDetails){
        userDetailRepository.upsertData(userData)

    }

    operator fun invoke(): Flow<List<UserDetails>> {
        return userDetailRepository.getAllUsers()
    }

//    suspend fun getUserById(id: Int) = userDetailReposirotory.getUserById(id)
//    suspend fun deleteUser(id: Int) = userDetailReposirotory.deleteUserById(id)


}