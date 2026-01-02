//package com.example.apiintegration.presentation.auth
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowForward
//import androidx.compose.material.icons.filled.ArrowForward
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import com.example.apiintegration.R
//import androidx.compose.ui.unit.sp
//import com.stfalcon.pinput.compose.Pinput
//import com.stfalcon.pinput.compose.data.PinTheme
//
//
//
//@Composable
//fun PinLoginScreen(
//    userName: String = "Romina",
//    onPinComplete: (String) -> Unit = {}
//) {
//    var pin by remember { mutableStateOf("") }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//
//        // 🔵 Top curved background (simple version)
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(260.dp)
//                .background(Color(0xFF2454F8))
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            Spacer(modifier = Modifier.height(140.dp))
//
//            // 👤 Avatar
//            Box(
//                modifier = Modifier
//                    .size(90.dp)
//                    .background(Color.White, CircleShape),
//                contentAlignment = Alignment.Center
//            ) {
//                Image(
//                    painter = painterResource(R.drawable.bubble_2), // your avatar
//                    contentDescription = "Profile",
//                    modifier = Modifier
//                        .size(72.dp)
//                        .clip(CircleShape)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            // 👋 Greeting
//            Text(
//                text = "Hello, $userName!!",
//                fontSize = 22.sp,
//                fontWeight = FontWeight.Bold
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = "Type your password",
//                color = Color.Gray,
//                fontSize = 14.sp
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // 🔐 PIN INPUT (4 DIGITS)
//            Pinput(
//                length = 4,
//                value = pin,
//                onValueChange = {
//                    pin = it
//                    if (it.length == 4) {
//                        onPinComplete(it)
//                    }
//                },
//                defaultPinTheme = PinTheme(
//                    width = 54.dp,
//                    height = 54.dp,
//                    textStyle = TextStyle(
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Bold
//                    ),
//                    backgroundColor = Color(0xFFF2F2F2),
//                    shape = RoundedCornerShape(12.dp)
//                ),
//                focusedPinTheme = PinTheme(
//                    backgroundColor = Color.White,
//                    border = BorderStroke(1.5.dp, Color(0xFF2454F8)),
//                    shape = RoundedCornerShape(12.dp)
//                )
//            )
//
//            Spacer(modifier = Modifier.weight(1f))
//
//            // 🔁 Not you?
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Not you?",
//                    color = Color.Gray
//                )
//
//                Spacer(modifier = Modifier.width(10.dp))
//
//                Box(
//                    modifier = Modifier
//                        .size(34.dp)
//                        .background(Color(0xFF2454F8), CircleShape),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.ArrowForward,
//                        contentDescription = "Switch user",
//                        tint = Color.White,
//                        modifier = Modifier.size(18.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//        }
//    }
//}
