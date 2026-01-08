package com.example.apiintegration.domain.repository

import com.example.apiintegration.data.remote.dto.Country
import com.example.apiintegration.domain.model.User

interface AuthRepository {

    // Network Operations
    suspend fun  login(username: String, password: String): Result<User>

    // Session Management
    fun isUserLoggedIn(): Boolean

    // From Hardcoded Data
    fun getCountries(): List<Country>


    // Local Storage Operations

}
