package com.example.apiintegration.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiintegration.domain.model.Post
import com.example.apiintegration.domain.model.PostList
import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.usecase.GetPostUseCase
import com.example.apiintegration.domain.usecase.GetPostsUseCase
import com.example.apiintegration.domain.usecase.local_post.UpsertPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getPostUseCase: GetPostUseCase,
    private val postsUseCase: UpsertPostUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<PostsUiState>(PostsUiState.Idle)
    val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()


    fun upsertPost(id: Int?=null, title: String,body : String) {
        if (title.isBlank() || body.isBlank()) return

        val user = PostList(id = id, title = title, body = body)
        viewModelScope.launch {
            postsUseCase(user)
        }
    }


    fun getPosts() {
        _uiState.value = PostsUiState.Loading

        viewModelScope.launch {
            try {
                val result = getPostsUseCase()

                result.onSuccess { postsList ->
                    _uiState.value = PostsUiState.Success(postsList)
                }.onFailure { error ->
                    _uiState.value = PostsUiState.Error(error.message ?: "Unknown error occurred")
                }
            } catch (e: Exception) {
                // Handle cancellation gracefully during activity destruction
                if (e !is kotlinx.coroutines.CancellationException) {
                    _uiState.value = PostsUiState.Error(e.message ?: "Unknown error occurred")
                }
            }
        }
    }

    fun getPost(id: Int) {
        _uiState.value = PostsUiState.Loading

        viewModelScope.launch {
            try {
                val result = getPostUseCase(id)

                result.onSuccess { post ->
                    _uiState.value = PostsUiState.Success(listOf(post))
                }.onFailure { error ->
                    _uiState.value = PostsUiState.Error(error.message ?: "Unknown error occurred")
                }
            } catch (e: Exception) {
                // Handle cancellation gracefully during activity destruction
                if (e !is kotlinx.coroutines.CancellationException) {
                    _uiState.value = PostsUiState.Error(e.message ?: "Unknown error occurred")
                }
            }
        }
    }
}

sealed class PostsUiState {
    object Idle : PostsUiState()
    object Loading : PostsUiState()
    data class Success(val data: List<Post>) : PostsUiState()
    data class Error(val message: String) : PostsUiState()
}
