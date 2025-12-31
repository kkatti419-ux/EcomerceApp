package com.example.apiintegration.domain.usecase.local_user

import com.example.apiintegration.domain.model.UserData.UserDetails
import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.repository.UserDetailReposirotory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDetailsUseCase @Inject constructor(private val userDetailReposirotory: UserDetailReposirotory) {
    suspend fun upsertData(userData: UserDetails){
        userDetailReposirotory.upsertData(userData)

    }

    operator fun invoke(): Flow<List<UserDetails>> {
        return userDetailReposirotory.getAllUsers()
    }

//    suspend fun getUserById(id: Int) = userDetailReposirotory.getUserById(id)
//    suspend fun deleteUser(id: Int) = userDetailReposirotory.deleteUserById(id)


}