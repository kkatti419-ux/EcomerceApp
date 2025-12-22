package com.example.apiintegration.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
// PostResponse.kt
data class PostsWrapper(
    @Json(name = "posts") val posts: List<PostResponse>,
    @Json(name = "total") val total: Int?,
    @Json(name = "skip") val skip: Int?,
    @Json(name = "limit") val limit: Int?
)

@JsonClass(generateAdapter = true)
data class PostResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "body") val body: String,
    @Json(name = "tags") val tags: List<String> = emptyList(),
    @Json(name = "reactions") val reactions: Reactions = Reactions(),
    @Json(name = "views") val views: Int = 0,
    @Json(name = "userId") val userId: Int = 0
)

@JsonClass(generateAdapter = true)
data class Reactions(
    @Json(name = "likes") val likes: Int = 0,
    @Json(name = "dislikes") val dislikes: Int = 0
)


