package com.example.apiintegration.domain.usecase.recipe

import com.example.apiintegration.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository){
    suspend operator fun invoke() = recipeRepository.getRecipe()
}