package com.example.apiintegration.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.apiintegration.common.ui.PrimaryButton
import com.example.apiintegration.common.ui.SvgImage
import com.example.apiintegration.domain.model.product.Product
import com.example.apiintegration.navigation.Screen


@Composable
fun ProductList(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),

) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is ProductUiState.Idle -> {
            // Call API once
            viewModel.getAllProducts()
        }

        is ProductUiState.Loading -> {
            Text("Loading...")
        }

        is ProductUiState.Success -> {
            ProductGrid(products = uiState.data.products,navController)
        }

        is ProductUiState.Error -> {
            Text(text = uiState.message, color = Color.Red)
        }
    }
}


@Composable
fun ProductGrid(products: List<Product>,navController: NavController) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product,navController)
        }
    }
}


@Composable
fun ProductItem(product: Product,navController: NavController) {

    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(6.dp)
            .clickable {
                navController.navigate(Screen.ProductDetailScreen.createRoute(product.id))
            },
        shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            SvgImage(
                imageUrl = product.thumbnail, // 👈 API image
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(Color(0xFFF5F5F5)),
                contentDescription = product.title
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.title,
                modifier = Modifier.padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 1, // or 2 if you want two lines
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = product.description,
                modifier = Modifier.padding(horizontal = 12.dp),
                color = Color.Gray,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${product.discountPercentage}% OFF",
                    color = Color(0xFF2E7D32),
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )

                Text(text = "⭐ ${product.rating}")
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "In stock: ${product.stock}",
                modifier = Modifier.padding(horizontal = 12.dp),
                color = Color(0xFF1565C0)
            )

            PrimaryButton(
                onClick = { /* Add to cart */ },
                text = "Add to Cart",
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            )
        }
    }
}
