package com.example.apiintegration.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.common.utils.AppLogger
import com.example.apiintegration.domain.model.Post
import com.example.apiintegration.domain.model.product.Product
import com.example.apiintegration.domain.model.product.ProductResponse
import com.example.apiintegration.domain.usecase.product.AllProductUseCase
import com.example.apiintegration.domain.usecase.product.GetProductByIdUseCase
import com.example.apiintegration.presentation.posts.PostsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    val allProductUseCase: AllProductUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Idle)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private val _productDetailState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Idle)
    val productDetailState: StateFlow<ProductDetailUiState> = _productDetailState.asStateFlow()

    fun getAllProducts(){
        _uiState.value= ProductUiState.Loading

        viewModelScope.launch {
            try {
                val res=allProductUseCase()
                AppLogger.d(res.toString(),"sjjsjs")
                res.onSuccess {
                    AppLogger.d(res.toString(),"EASY")

                    _uiState.value= ProductUiState.Success(it)
                }.onFailure {
                    _uiState.value= ProductUiState.Error(it.message?:"Unknown error occurred")
                }


            }
            catch (e: Exception)
            {

            }

        }

    }

    fun getProductById(id: Long) {
        _productDetailState.value = ProductDetailUiState.Loading

        viewModelScope.launch {
            try {
                val result = getProductByIdUseCase(id)
                result.onSuccess { product ->
                    _productDetailState.value = ProductDetailUiState.Success(product)
                }.onFailure { error ->
                    _productDetailState.value = ProductDetailUiState.Error(
                        error.message ?: "Unknown error occurred"
                    )
                }
            } catch (e: Exception) {
                // Handle cancellation gracefully during activity destruction
                if (e !is kotlinx.coroutines.CancellationException) {
                    _productDetailState.value = ProductDetailUiState.Error(
                        e.message ?: "Unknown error occurred"
                    )
                }
            }
        }
    }

}


sealed class ProductUiState {
    object Idle : ProductUiState()
    object Loading : ProductUiState()
    data class Success(val data: ProductResponse) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}

sealed class ProductDetailUiState {
    object Idle : ProductDetailUiState()
    object Loading : ProductDetailUiState()
    data class Success(val product: Product) : ProductDetailUiState()
    data class Error(val message: String) : ProductDetailUiState()
}