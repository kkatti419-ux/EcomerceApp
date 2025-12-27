package com.example.apiintegration.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.common.utils.AppLogger
import com.example.apiintegration.domain.model.Post
import com.example.apiintegration.domain.model.product.ProductResponse
import com.example.apiintegration.domain.usecase.product.AllProductUseCase
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

): ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Idle)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()
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

}


sealed class ProductUiState {
    object Idle : ProductUiState()
    object Loading : ProductUiState()
    data class Success(val data: ProductResponse) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}