package com.example.apiintegration.data.repository

import com.example.apiintegration.data.remote.api.RecipeApi
import com.example.apiintegration.domain.model.recipe.Recipe
import com.example.apiintegration.domain.model.recipe.RecipeResponse
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val recipe: RecipeApi){
    suspend fun getRecipe(): Result<RecipeResponse> {
        return try {
            val response=recipe.getRecipes();
            Result.success(response)
        }
        catch (e: Exception){
            Result.failure(e)

        }

    }

}