package com.example.apiintegration.data.remote.api

import com.example.apiintegration.domain.model.product.ProductResponse
import com.example.apiintegration.domain.model.recipe.RecipeResponse
import retrofit2.http.GET

interface RecipeApi {
    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse
}