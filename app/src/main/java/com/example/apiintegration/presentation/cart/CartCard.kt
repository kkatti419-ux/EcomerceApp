package com.example.apiintegration.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apiintegration.domain.model.Cart.Cart

@Composable
fun CartCard(cart: Cart,navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth().background(color = Color.White),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            CartHeader(cart)

            Spacer(modifier = Modifier.height(8.dp))

            cart.products.forEach { product ->
                ProductItem1(product,navController,)
                Divider()
            }

            Spacer(modifier = Modifier.height(8.dp))

            CartFooter(cart)
        }
    }
}
