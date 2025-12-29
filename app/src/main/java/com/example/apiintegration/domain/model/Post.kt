package com.example.apiintegration.domain.model

// Post.kt
// Domain model for Post
// This is a pure Kotlin data class with no framework dependencies
// It represents the business entity, not the API response structure

data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val tags: List<String>,
    val likes: Int,
    val dislikes: Int,
    val views: Int,
    val userId: Int
)


