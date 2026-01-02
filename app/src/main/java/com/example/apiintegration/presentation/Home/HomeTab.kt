package com.example.apiintegration.presentation.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apiintegration.R
import com.example.apiintegration.common.ui.OrderChip
import com.example.apiintegration.common.ui.video.VideoStoryCard
import com.example.apiintegration.navigation.Screen

@Composable
fun HomeTab(controller: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // 🔝 Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.camera_with_circle),
                contentDescription = null,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.width(12.dp))

            Text(
                text = "My Activity",
                modifier = Modifier
                    .background(Color(0xFF2454F8), RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = Color.White
            )

            Spacer(Modifier.weight(1f))

            Icon(Icons.Outlined.Settings, null)
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Hello, Romina!", fontSize = 28.sp, fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        // 📢 Announcement Card
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF6F6F6), RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text("Announcement", fontWeight = FontWeight.Bold)
                Text(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color(0xFF2454F8), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // 👀 Recently viewed
        Text("Recently viewed", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(5) {
                Image(
                    painter = painterResource(R.drawable.cloth),
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .clickable {
                            controller.navigate(Screen.ProductList.route)
                        }


                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // 📦 Orders
        Text("My Orders", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),

            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OrderChip("To Pay")
            OrderChip("To Receive", highlight = true)
            OrderChip("To Review")
            OrderChip("To Review")
        }

        Spacer(Modifier.height(24.dp))

        // 🎥 Stories
        Text("Stories", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            listOf(
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            ).forEachIndexed { index, url ->

                VideoStoryCard(
                    thumbnailRes = R.drawable.thumbnail1,
                    videoUrl = url,
                )
            }
        }
    }
}



@Composable
fun ColorScreen(color: Color, title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color=color), contentAlignment = Alignment.Center
    ) {
        Text(title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}


