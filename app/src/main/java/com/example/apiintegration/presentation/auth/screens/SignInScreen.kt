package com.example.apiintegration.presentation.auth.screens

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.apiintegration.navigation.Screen
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apiintegration.R
import com.example.apiintegration.common.ui.AppOutlinedTextField
import com.example.apiintegration.common.ui.CountryPhoneTextField
import com.example.apiintegration.common.ui.PrimaryButton
import com.example.apiintegration.common.ui.ProfileImagePicker
import com.example.apiintegration.data.remote.dto.Country
import com.example.apiintegration.presentation.auth.AuthUiState
import com.example.apiintegration.presentation.auth.AuthViewModel

@Composable
fun SignInScreenContent(
    username: String,
    password: String,
    phone: String,
    selectedCountry: Country,
    countries: List<Country>,
    profileImageUri: Uri?,
    isLoading: Boolean,
    uiState: AuthUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onCountrySelected: (Country) -> Unit,
    onProfileImagePicked: (Uri?) -> Unit,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.bubble_2),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.TopEnd)
                .offset(x = 120.dp, y = (-30).dp)
        )

        Image(
            painter = painterResource(R.drawable.bubble_1),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.TopStart)
                .offset(x = 30.dp, y = 20.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(28.dp))

            Text(
                text = "Create\nAccount",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(16.dp))

            ProfileImagePicker(
                imageUri = profileImageUri,
                onImagePicked = onProfileImagePicked,
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.Start)
            )

            Spacer(Modifier.height(24.dp))

            AppOutlinedTextField(
                value = username,
                onValueChange = onUsernameChange,
                label = "Username"
            )

            Spacer(Modifier.height(12.dp))

            AppOutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = "Password"
            )

            Spacer(Modifier.height(12.dp))

            CountryPhoneTextField(
                phoneNumber = phone,
                onPhoneChange = onPhoneChange,
                selectedCountry = selectedCountry,
                onCountrySelected = onCountrySelected,
                countries = countries
            )

            Spacer(Modifier.height(24.dp))

            PrimaryButton(
                onClick = onSubmit,
                text = if (isLoading) "Loading..." else "Create Account",
                enabled = !isLoading
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Cancel",
                modifier = Modifier.clickable { onCancel() },
                color = MaterialTheme.colorScheme.primary
            )

            if (uiState is AuthUiState.Error) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = uiState.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}


@Composable
fun SignInScreen(
    navController: NavController,
    onLoginSuccess: (String, String) -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val countries by viewModel.countries.collectAsState()
    val profileImageUri by viewModel.profileImage.collectAsState()

    var username by remember { mutableStateOf("emilys") }
    var password by remember { mutableStateOf("emilyspass") }
    var phone by remember { mutableStateOf("") }
    var selectedCountry by remember {
        mutableStateOf(Country("India", "IN", "+91"))
    }

    val isLoading = uiState is AuthUiState.Loading
    val context = LocalContext.current   // ✅ THIS LINE


    SignInScreenContent(
        username = username,
        password = password,
        phone = phone,
        selectedCountry = selectedCountry,
        countries = countries,
        profileImageUri = profileImageUri,
        isLoading = isLoading,
        uiState = uiState,
        onUsernameChange = { username = it },
        onPasswordChange = { password = it },
        onPhoneChange = { phone = it },
        onCountrySelected = { selectedCountry = it },

        // ✅ FIXED: adapter lambda
        onProfileImagePicked = { uri ->
            uri?.let { viewModel.onProfileImageSelected(it) }
        },

        onSubmit = { viewModel.loginValidation(username, password,phone,profileImageUri,context) },
        onCancel = { navController.popBackStack() }
    )


    if (uiState is AuthUiState.Success) {
        LaunchedEffect(Unit) {
            navController.navigate(Screen.OtpInputField.route)
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewSignInScreen() {

    SignInScreenContent(
        username = "emilys",
        password = "password123",
        phone = "9876543210",
        selectedCountry = Country("India", "IN", "+91"),
        countries = listOf(
            Country("India", "IN", "+91"), Country("USA", "US", "+1")
        ),
        profileImageUri = null,
        isLoading = false,
        uiState = AuthUiState.Idle,
        onUsernameChange = {},
        onPasswordChange = {},
        onPhoneChange = {},
        onCountrySelected = {},
        onProfileImagePicked = {},
        onSubmit = {},
        onCancel = {}
    )
}
