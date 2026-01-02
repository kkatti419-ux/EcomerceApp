package com.example.apiintegration.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apiintegration.common.ui.AppOutlinedTextField
import com.example.apiintegration.R
import com.example.apiintegration.common.ui.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),

    onLoginSuccess: (String) -> Unit,
    onForgotPassword: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(

    ) { padding ->

        // ✅ USE Scaffold padding ONLY (NO safeDrawingPadding here)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(vertical = 40.dp, horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Welcome\nBack!",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Black
                    )
                }


                Spacer(modifier = Modifier.height(24.dp))
                AppOutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = "Username",
                    leadingIcon =
                        {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "User Icon",
                                modifier = Modifier.size(20.dp)
                            )
                        }


                )
                AppOutlinedTextField(

                    value = username,
                    onValueChange = { username = it },
                    label = "Username",
                    leadingIcon =
                        {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "User Icon",
                                modifier = Modifier.size(20.dp)
                            )
                        }


                )

                Spacer(modifier = Modifier.height(40.dp)) // 👈 pushes button
                PrimaryButton(
                    text = "Continue",
                    onClick = { },
                    backgroundColor = MaterialTheme.colorScheme.background
//                        Color.Black
                )




                when (val state = uiState) {
                    is AuthUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is AuthUiState.Error -> {
                        Text(
                            text = state.message,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    is AuthUiState.Success -> {
                        val user = state.user
                        LaunchedEffect(user) {
                            onLoginSuccess(user.username)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(12.dp))
                            Text("Login Successful! Redirecting…")
                        }
                    }

                    else -> Unit
                }
            }
        }
    }
}
