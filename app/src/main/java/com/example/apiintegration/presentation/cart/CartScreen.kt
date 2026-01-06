package com.example.apiintegration.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),


) {
    val uiState by cartViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        cartViewModel.getCarts()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)

    ) {
        when (uiState) {

            CartsUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is CartsUiState.Success -> {
                val carts = (uiState as CartsUiState.Success).data

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(carts) { cart ->
                        CartCard(cart, navController)
                    }
                }
            }

            is CartsUiState.Error -> {
                Text(
                    text = (uiState as CartsUiState.Error).message,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red
                )
            }

            CartsUiState.Idle -> {
                Text(
                    text = "Preparing your carts…",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )
            }
        }
    }
}
