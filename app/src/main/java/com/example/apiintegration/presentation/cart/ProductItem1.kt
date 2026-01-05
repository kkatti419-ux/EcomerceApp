package com.example.apiintegration.presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.apiintegration.domain.model.Cart.Product


@Composable
fun ProductItem1(product: Product,navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = product.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = product.title,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )

            Text(
                text = "₹${product.price} × ${product.quantity}",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Text(
                text = "₹${product.discountedTotal}",
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "-${product.discountPercentage}%",
            color = Color.Green,
            fontWeight = FontWeight.Bold
        )
    }
}
