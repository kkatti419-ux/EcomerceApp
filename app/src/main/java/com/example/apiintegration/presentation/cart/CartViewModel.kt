package com.example.apiintegration.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.domain.model.Cart.Cart
import com.example.apiintegration.domain.usecase.cart.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCase: GetCartUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CartsUiState>(CartsUiState.Idle)
    val uiState: StateFlow<CartsUiState> = _uiState.asStateFlow()

    fun getCarts() {
        viewModelScope.launch {
            _uiState.value = CartsUiState.Loading

            try {
                useCase().onSuccess { result ->
                    _uiState.value = CartsUiState.Success(result.carts)
                }.onFailure { error ->
                    _uiState.value = CartsUiState.Error(
                        error.message ?: "Unknown error"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = CartsUiState.Error(
                    e.message ?: "Something went wrong"
                )
            }
        }
    }
}

//
//@HiltViewModel
//class CartViewModel @Inject constructor(private val useCase: GetCartUseCase) : ViewModel() {
//    private val _uiState = MutableStateFlow<CartsUiState>(CartsUiState.Idle)
//
//    val uiState: StateFlow<CartsUiState> = _uiState.asStateFlow()
//
//    fun getCarts() {
//        _uiState.value = CartsUiState.Loading
//        viewModelScope.launch {
//            try {
//                val result = useCase.invoke()
//                result.onSuccess { carts ->
//                    _uiState.value = CartsUiState.Success(carts.carts)
//                }
//                    .onFailure { error ->
//                        _uiState.value =
//                            CartsUiState.Error(error.message ?: "Unknown error occurred")
//                    }
//
//            } catch (e: Exception) {
//
//
//            }
//        }
//
//    }
//}

//sealed class CartsUiState {
//    object Idle : CartsUiState()
//    object Loading : CartsUiState()
//    data class Success(val data: List<Cart>) : CartsUiState()
//    data class Error(val message: String) : CartsUiState()
//}

sealed class CartsUiState {
    object Idle : CartsUiState()
    object Loading : CartsUiState()
    data class Success(val data: List<Cart>) : CartsUiState()
    data class Error(val message: String) : CartsUiState()
}
