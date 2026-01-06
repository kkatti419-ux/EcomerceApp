package com.example.apiintegration.presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apiintegration.domain.model.Cart.Cart

@Composable
fun CartHeader(cart: Cart) {
    Column {
        Text(
            text = "Cart #${cart.id}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = buildString {
                append("Products: ")
                append(cart.totalProducts)
                append("  |  Quantity: ")
                append(cart.totalQuantity)
            },
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}
