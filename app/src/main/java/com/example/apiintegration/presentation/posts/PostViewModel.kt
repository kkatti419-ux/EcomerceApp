package com.example.apiintegration.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.data.remote.dto.PostResponse
import com.example.apiintegration.domain.model.Post
import com.example.apiintegration.domain.model.Root
import com.example.apiintegration.domain.repository.PostRepository
import com.example.apiintegration.domain.usecase.GetPostUseCase
import com.example.apiintegration.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//@HiltViewModel
//class PostViewModel @Inject constructor(
//    private val getPostsUseCase: GetPostsUseCase,
//    private val getPostUseCase: GetPostUseCase
//): ViewModel() {
//    private val _uiState = MutableStateFlow<PostsUiState>(PostsUiState.Idle)
//    val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()
//
//    fun getPosts() {
//        _uiState.value = PostsUiState.Loading
//
//        viewModelScope.launch {
//            try {
//                val result = getPostsUseCase()
//
//                result.onSuccess { postsList ->
//                    _uiState.value = PostsUiState.Success(postsList)
//                }.onFailure { error ->
//                    _uiState.value = PostsUiState.Error(error.message ?: "Unknown error occurred")
//                }
//            } catch (e: Exception) {
//                // Handle cancellation gracefully during activity destruction
//                if (e !is kotlinx.coroutines.CancellationException) {
//                    _uiState.value = PostsUiState.Error(e.message ?: "Unknown error occurred")
//                }
//            }
//        }
//    }
//
//    fun getPost() {
//        _uiState.value = PostsUiState.Loading
//
//        viewModelScope.launch {
//            try {
//                val result = getPostUseCase()
//
//                result.onSuccess { post ->
//                    _uiState.value = PostsUiState.Success(listOf(post))
//                }.onFailure { error ->
//                    _uiState.value = PostsUiState.Error(error.message ?: "Unknown error occurred")
//                }
//            } catch (e: Exception) {
//                // Handle cancellation gracefully during activity destruction
//                if (e !is kotlinx.coroutines.CancellationException) {
//                    _uiState.value = PostsUiState.Error(e.message ?: "Unknown error occurred")
//                }
//            }
//        }
//    }
//
//}
//
sealed class PostsUiState {
    object Idle : PostsUiState()
    object Loading : PostsUiState()
    data class Success<Post>(val data: Post) : PostsUiState()
    data class Error(val message: String) : PostsUiState()
}
//



@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _posts = MutableStateFlow<List<PostResponse>>(emptyList())
    val posts = _posts.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error

    fun loadPosts() = viewModelScope.launch {
        repository.getPosts()
            .onSuccess { list ->
                _posts.value = list
            }
            .onFailure { e ->
                _error.value = e.message
            }
    }

    fun loadSinglePost() = viewModelScope.launch {
        repository.getPost()
            .onSuccess { post ->
                // handle post
            }
            .onFailure { e ->
                _error.value = e.message
            }
    }

}

