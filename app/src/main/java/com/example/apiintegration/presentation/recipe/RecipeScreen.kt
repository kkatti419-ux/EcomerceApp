package com.example.apiintegration.presentation.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apiintegration.common.ui.ErrorView
import com.example.apiintegration.domain.model.recipe.Recipe

@Composable
fun RecipeScreen(
    navController: NavController,
    uiState: RecipeUiState,
    onRetry: () -> Unit = {}
) {
    when (uiState) {
        is RecipeUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is RecipeUiState.Success -> {
            RecipeList(
                recipes = uiState.recipes,
                onRecipeClick = { recipe ->
                    navController.navigate("recipe_detail/${recipe.id}")
                }
            )
        }

        is RecipeUiState.Error -> {
            ErrorView(
                message = uiState.message,
                onRetry = onRetry
            )
        }

        RecipeUiState.Idle -> Unit
    }
}

@Composable
fun RecipeScreenRoute(
    navController: NavController,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getRecipes()
    }

    RecipeScreen(
        navController = navController,
        uiState = uiState,
        onRetry = { viewModel.getRecipes() }
    )
}


@Preview(showBackground = true)
@Composable
fun RecipeScreenSuccessPreview() {
    RecipeScreen(
        navController = rememberNavController(),
        uiState = RecipeUiState.Success(
            recipes = listOf(previewRecipe())
        )
    )
}


private fun previewRecipe() = Recipe(
    id = 1,
    name = "Butter Chicken",
    ingredients = listOf("Chicken", "Butter", "Spices"),
    instructions = listOf("Cook chicken", "Add sauce"),
    prepTimeMinutes = 20,
    cookTimeMinutes = 30,
    servings = 4,
    difficulty = "Medium",
    cuisine = "Indian",
    caloriesPerServing = 450,
    tags = listOf("Dinner", "Spicy"),
    userId = 1,
    image = "https://via.placeholder.com/400",
    rating = 4.6,
    reviewCount = 120,
    mealType = listOf("Dinner")
)

