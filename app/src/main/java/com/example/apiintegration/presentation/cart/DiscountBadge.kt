package com.example.apiintegration.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DiscountBadge(discount: Double) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFE8F5E9))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = "-${discount.toInt()}%",
            color = Color(0xFF2E7D32),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}
