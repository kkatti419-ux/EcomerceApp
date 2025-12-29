package com.example.apiintegration.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.apiintegration.common.ui.PrimaryButton
import com.example.apiintegration.common.ui.SvgImage

@Composable
fun ProductDetailScreen(
    productId: Long,
    navController: NavController? = null,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val productDetailState by viewModel.productDetailState.collectAsState()

    LaunchedEffect(productId) {
        if (productId > 0) {
            viewModel.getProductById(productId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (val state = productDetailState) {
            is ProductDetailUiState.Idle -> {
                // Initial state, waiting for data
            }

            is ProductDetailUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ProductDetailUiState.Success -> {
                val product = state.product
                
                SvgImage(
                    imageUrl = product.thumbnail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color(0xFFF5F5F5)),
                    contentDescription = product.title
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = product.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.description,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "⭐ ${product.rating}",
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Discount: ${product.discountPercentage}%",
                    color = Color(0xFF2E7D32)
                )

                Text(
                    text = "Stock: ${product.stock}",
                    color = Color(0xFF1565C0)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PrimaryButton(
                        onClick = { /* BUY NOW dummy */ },
                        text = "Buy Now",
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    PrimaryButton(
                        onClick = { navController?.popBackStack() },
                        text = "Cancel",
                        modifier = Modifier
                    )
                }
            }

            is ProductDetailUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Error: ${state.message}",
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        PrimaryButton(
                            onClick = { navController?.popBackStack() },
                            text = "Go Back"
                        )
                    }
                }
            }
        }
    }
}
