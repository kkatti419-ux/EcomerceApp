package com.example.apiintegration.common.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apiintegration.navigation.Screen

@Composable
fun OtpInputField(
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit
) {
    val otpValues = remember {
        List(otpLength) { mutableStateOf("") }
    }

    val focusRequesters = remember {
        List(otpLength) { FocusRequester() }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterHorizontally
        ),

        modifier = Modifier.fillMaxWidth(),
    ) {
        otpValues.forEachIndexed { index, state ->
            OutlinedTextField(
                value = state.value,
                onValueChange = { value ->
                    if (value.length <= 1) {
                        state.value = value

                        if (value.isNotEmpty() && index < otpLength - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }

                        if (otpValues.all { it.value.isNotEmpty() }) {
                            onOtpComplete(otpValues.joinToString("") { it.value })
                        }
                    }
                },
                modifier = Modifier
                    .width(50.dp)
                    .focusRequester(focusRequesters[index]),
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                )
            )
        }
    }
}
