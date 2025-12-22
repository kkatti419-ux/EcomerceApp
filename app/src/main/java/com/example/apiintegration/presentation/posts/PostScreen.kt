package com.example.apiintegration.presentation.posts//package com.example.apiintegration.presentation.posts
//
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.CircularProgressIndicator
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.apiintegration.data.remote.dto.PostResponse
//
//
//@Composable
//fun PostScreen(
////    viewModel: PostViewModel = viewModel(),
//    viewModel: PostViewModel = hiltViewModel(),
//
//    onLogout: () -> Unit,
//) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    LaunchedEffect(key1 = true) {
//        viewModel.getPosts() // Or getPost(), depending on what you want to show initially
//    }
//
//    when (val state = uiState) {
//        is PostsUiState.Loading -> {
//            CircularProgressIndicator()
//        }
//
//        is PostsUiState.Success<*> -> {
//            val posts = state.data as? List<Post> ?: emptyList()
//            LazyColumn {
//                items(posts) { post ->
//                    Text(text = post.title)
//                }
//            }
//        }
//
//        is PostsUiState.Error -> {
//            Text("Error: ${state.message}")
//        }
//
//        is PostsUiState.Idle -> {}
//    }
//}
//
//
////@Composable
////fun PostScreen(
////    viewModel: PostViewModel = hiltViewModel(),
////    onLogout: () -> Unit,
////) {
////    val uiState by viewModel.uiState.collectAsState()
////
////    LaunchedEffect(Unit) {
////        viewModel.getPosts()
////    }
////
////    when (val state = uiState) {
////
////        is PostsUiState.Loading -> {
////            CircularProgressIndicator()
////        }
////
////        is PostsUiState.Success<*> -> {
////            val posts = state.data as? List<PostResponse> ?: emptyList()
////
////            LazyColumn {
////                items(posts) { post ->
////                    Text(text = post.title)
////                }
////            }
////        }
////
////        is PostsUiState.Error -> {
////            Text("Error: ${state.message}")
////        }
////
////        is PostsUiState.Idle -> Unit
////    }
////}
////
//
