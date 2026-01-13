package com.example.apiintegration.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.common.utils.AppLogger
import com.example.apiintegration.domain.model.recipe.Recipe
import com.example.apiintegration.domain.model.recipe.RecipeResponse
import com.example.apiintegration.domain.repository.RecipeRepository
import com.example.apiintegration.domain.usecase.recipe.GetRecipeUseCase
import com.example.apiintegration.presentation.cart.CartsUiState
import com.example.apiintegration.presentation.form.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.onSuccess

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Idle)
    val uiState: StateFlow<RecipeUiState> = _uiState


    fun getRecipes() {
        viewModelScope.launch {
            _uiState.value = RecipeUiState.Loading

            try {
                getRecipeUseCase()
                    .onSuccess { result: RecipeResponse ->
                        _uiState.value = RecipeUiState.Success(
                            result.recipes
                        )
                    }
                    .onFailure { error: Throwable ->
                        _uiState.value = RecipeUiState.Error(
                            error.message ?: "Unknown error"
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = RecipeUiState.Error(
                    e.message ?: "Something went wrong"
                )
                AppLogger.d(e.toString(), "Recipe Error")
            }
        }
    }

}


sealed class RecipeUiState {
    object Idle : RecipeUiState()
    object Loading : RecipeUiState()
    data class Success(val recipes: List<Recipe>) : RecipeUiState()
    data class Error(val message: String) : RecipeUiState()
}
