package com.example.apiintegration.domain.model.recipe


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val recipes: List<Recipe>,
    val total: Long,
    val skip: Long,
    val limit: Long,
)