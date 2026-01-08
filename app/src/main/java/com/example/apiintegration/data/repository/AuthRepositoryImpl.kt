package com.example.apiintegration.data.repository

import com.example.apiintegration.data.local.UserPreferencesStorage
import com.example.apiintegration.data.local.datasource.CountryDataSource
import com.example.apiintegration.data.local.session.SessionManager
import com.example.apiintegration.data.remote.api.AuthApi
import com.example.apiintegration.data.remote.dto.Country
import com.example.apiintegration.data.remote.dto.LoginRequest
import com.example.apiintegration.data.remote.dto.LoginResponse
import com.example.apiintegration.domain.model.User
import com.example.apiintegration.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthApi,
    private val authLocalDataSource: UserPreferencesStorage,
    private val sessionManager: SessionManager,
) : AuthRepository {



    override suspend fun login(username: String, password: String): Result<User> {
        return try {
            val request = LoginRequest(
                username = username,
                password = password,
                expiresInMins = 30 // Default value
            )
            val response = authRemoteDataSource.authentication("application/json", request)

            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override fun isUserLoggedIn(): Boolean {
        return sessionManager.isLoggedIn()
    }

    override fun getCountries(): List<Country> {
        return CountryDataSource.asianCountries
    }




    private fun LoginResponse.toDomain(): User {
        return User(
            id = id,
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            image = image,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
