package com.example.apiintegration.domain.model

data class UserProfile(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val place:String,
    val age:String
)
