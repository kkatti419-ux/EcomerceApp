package com.example.apiintegration.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigator
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: (String) -> Unit,
    onForgotPassword: (String) -> Unit,

// ✨ new function
//    Unit = means function returns nothing.
//    Equivalent to void in Java.
) {
    val uiState by viewModel.uiState.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .padding(
                WindowInsets.systemBars.asPaddingValues()   // <-- FIXES ENTIRE SCREEN
            ),

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Hello") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF6A5AE0),   // Your color here
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,


                    ),
//                                windowInsets = WindowInsets.statusBars   // <-- IMPORTAN
            )
        },

        bottomBar =
            {
                Button(
                    onClick = {
                        viewModel.sendPrompt(username, password)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF6A5AE0),
                                        Color(0xFF8A7CF4)
                                    )
                                ),
                                shape = RoundedCornerShape(14.dp)
                            )
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Login",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
    ) {


            padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text("Login", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(20.dp))

                // Username Input
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(14.dp),
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Input
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(14.dp),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                            Icon(
//                                imageVector = if (passwordVisible)
//                                    Icons.Default.Visibility
//                                else Icons.Default.VisibilityOff,
//                                contentDescription = "Toggle Password Visibility"
//                            )
                        }
                    },
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(24.dp))


                Spacer(modifier = Modifier.height(20.dp))

                // Handle UI States
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

                        // Trigger navigation immediately with proper key to prevent re-triggering
                        // Using user as key ensures this only runs once per successful login


//                        LaunchedEffect(key1 = user) {
////                            onLoginSuccess(user.username)
//                            onForgotPassword(user.username)
//                        }

                        // Show minimal UI during navigation transition
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Login Successful! Redirecting...",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}


/*
✅ Is LaunchedEffect like triggering a function or like Flutter’s initState()?
Short Answer:

✔ It behaves similar to Flutter's initState(),
✔ but it is also used to trigger functions / side effects when a certain state changes.

It is basically:

Flutter’s initState() + setState listeners + side-effect trigger

all combined.
*/

