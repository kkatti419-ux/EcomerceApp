package com.example.apiintegration.presentation.cart.screens

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
import androidx.compose.ui.unit.dp
import com.example.apiintegration.domain.model.Cart.Cart
import com.example.apiintegration.ui.theme.BluePrimary

@Composable
fun CartFooter(cart: Cart) {
    val savings = cart.total - cart.discountedTotal

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Divider(color = Color.LightGray, thickness = 1.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total",
                color = Color.Gray
            )
            Text(
                text = "₹${cart.total}",
                fontWeight = FontWeight.Medium
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Discounted Price",
                color = Color.Gray
            )
            Text(
                text = "₹${cart.discountedTotal}",
                fontWeight = FontWeight.Bold
            )
        }

        if (savings > 0) {
            Text(
                text = "🎉 You saved ₹$savings",
                color = BluePrimary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
