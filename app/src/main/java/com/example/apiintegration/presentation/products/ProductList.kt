package com.example.apiintegration.presentation.products

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.apiintegration.common.ui.PrimaryButton
import com.example.apiintegration.common.ui.SvgImage

@Composable
fun ProductList() {
    val data = List(15) { "Product ${it + 1}" }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        items(data) { item ->
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .height(260.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // 🔹 Product Image
                    SvgImage(
                        imageUrl = "file:///android_asset/banana.svg",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .background(Color(0xFFF5F5F5)),
                        contentDescription = "product image"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // 🔹 Title
                    Text(
                        text = item,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )

                    // 🔹 Description
                    Text(
                        text = "Fresh and organic product with premium quality.",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = Color.Gray,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // 🔹 Discount + Rating Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "10.48% OFF",
                            color = Color(0xFF2E7D32),
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )

                        Text(
                            text = "⭐ 2.56",
                            color = Color.DarkGray
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // 🔹 Stock info
                    Text(
                        text = "In stock: 99",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = Color(0xFF1565C0)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // 🔹 CTA Button
                    PrimaryButton(
                        onClick = { },
                        text = "Add to Cart",
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}
