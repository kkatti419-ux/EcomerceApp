package com.example.apiintegration.presentation.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FetchDataFromRoom(
    viewModel: UserRoomDetailsViewModel = hiltViewModel(),

    ) {


    val uiState by viewModel.uiState.collectAsState()


    when (val state = uiState) {


        is UserRoomUiState.Error -> {
            Column() {
                Text(text = state.message)
            }
        }

        UserRoomUiState.Loading -> {
            Column() {
                Text(text = "Loading")
            }
        }

        is UserRoomUiState.Success -> {
            LazyColumn() {

                items(state.users.size) { index ->
                    Text(text = state.users[index].id.toString())
                    Text(text = state.users[index].username)
                    Text(text = state.users[index].password)
                    Text(text = state.users[index].token)
                }
            }
        }

    }


}


