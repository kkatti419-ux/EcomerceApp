package com.example.apiintegration.presentation.auth

import AppDialogueBox
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apiintegration.common.ui.OtpInputField
import com.example.apiintegration.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    phoneNumber: String = "+91 98765 43210",
    onOtpVerified: (String) -> Unit = {},
    onResendOtp: () -> Unit = {},
    navController: NavController

) {

    val correctOtp = "1234"
    val maxAttempts = 3

    var otp by remember { mutableStateOf("") }
    var attemptCount by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    var timeLeft by remember { mutableStateOf(60) }
    var isResendEnabled by remember { mutableStateOf(false) }

    // ⏱️ Timer logic
    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else {
            isResendEnabled = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "OTP Verification",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Enter the 4 digit code sent to",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = phoneNumber,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔢 OTP INPUT
        OtpInputField(
            otpLength = 4,
            onOtpComplete = { enteredOtp ->
                otp = enteredOtp
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ⏳ Timer
        if (!isResendEnabled) {
            Text(
                text = "Resend OTP in 00:${timeLeft.toString().padStart(2, '0')}",
                color = Color.Gray
            )
        } else {
            Text(
                text = "Resend OTP",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    timeLeft = 60
                    isResendEnabled = false
                    onResendOtp()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // 🔵 Verify Button
        Button(
            onClick = {
                if (otp == correctOtp) {
                    onOtpVerified(otp)
                    navController.navigate(route = Screen.IntroScreen.route)
                } else {
                    attemptCount++

                    if (attemptCount >= maxAttempts) {
                        showDialog = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = otp.length == 4 && !showDialog
        ) {
            Text("Verify OTP")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }

    // 🚨 Max attempts dialog (OUTSIDE Column)
    AppDialogueBox(
        show = showDialog,
        onDismiss = {
            showDialog = false
            attemptCount = 0   // optional reset
            otp = ""
        }
    )
}
