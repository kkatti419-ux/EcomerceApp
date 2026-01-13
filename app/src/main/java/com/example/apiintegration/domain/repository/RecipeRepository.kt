package com.example.apiintegration.domain.repository

import com.example.apiintegration.domain.model.recipe.RecipeResponse

interface RecipeRepository {
    suspend fun getRecipe(): Result<RecipeResponse>

}