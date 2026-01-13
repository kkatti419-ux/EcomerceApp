package com.example.apiintegration.presentation.recipe

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.apiintegration.domain.model.recipe.Recipe

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(recipes) { recipe ->
            RecipeCard(
                recipe = recipe,
                onClick = { onRecipeClick(recipe) }
            )
        }
    }
}

