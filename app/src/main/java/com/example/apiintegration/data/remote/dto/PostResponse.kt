package com.example.apiintegration.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
// PostResponse.kt
data class PostsWrapper(
    val posts: List<PostResponse>,
    val total: Int?,
    val skip: Int?,
    val limit: Int?
)

data class PostResponse(
    val id: Int,
    val title: String,
    val body: String,
    val tags: List<String> = emptyList(),
    val reactions: Reactions = Reactions(),
    val views: Int = 0,
    val userId: Int = 0
)

data class Reactions(
    val likes: Int = 0,
    val dislikes: Int = 0
)


