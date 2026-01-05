package com.example.apiintegration.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.apiintegration.domain.model.Cart.Cart

@Composable
fun CartFooter(cart: Cart) {
    val savings = cart.total - cart.discountedTotal

    Column {
        Divider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total")
            Text("₹${cart.total}")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Discounted")
            Text(
                text = "₹${cart.discountedTotal}",
                fontWeight = FontWeight.Bold
            )
        }

        if (savings > 0) {
            Text(
                text = "You saved ₹$savings 🎉",
                color = Color.Green,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
