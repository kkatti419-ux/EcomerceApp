package com.example.apiintegration.presentation.starter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apiintegration.common.ui.PrimaryButton
import com.example.apiintegration.common.ui.SvgImage
import com.example.apiintegration.common.ui.Dimens.Dimens
import com.example.apiintegration.navigation.Screen

@Composable
fun AppStartScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = Dimens.ScreenPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))

        // 🔵 Icon with circular background
        Box(
            modifier = Modifier
                .size(96.dp)
                .shadow(8.dp, CircleShape)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            SvgImage(
                imageUrl = "file:///android_asset/bag.svg",
                contentDescription = "Bag Icon",
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🖤 Title
        Text(
            text = "Shoppe",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 🩶 Subtitle
        Text(
            text = "Beautiful eCommerce UI Kit\nfor your online store",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        // 🔵 Primary Button
        PrimaryButton(
            onClick = {navController.navigate(Screen.StartScreen.route) },
            text = "Let's get started",
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 👇 Bottom text + arrow
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "I already have an account",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFF2454F8), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                SvgImage(
                    imageUrl = "file:///android_asset/right_arrow.svg",
                    contentDescription = "Arrow",
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
