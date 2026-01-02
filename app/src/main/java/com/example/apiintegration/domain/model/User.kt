package com.example.apiintegration.domain.model

// User.kt
// Domain model for User
// This is a pure Kotlin data class with no framework dependencies
// It represents the business entity, not the API response structure

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String
)


