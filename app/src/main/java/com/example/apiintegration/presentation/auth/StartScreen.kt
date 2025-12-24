package com.example.apiintegration.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apiintegration.R
import com.example.apiintegration.common.ui.AppOutlinedTextField
import com.example.apiintegration.common.ui.PrimaryButton
import com.example.apiintegration.common.ui.ProfileImagePicker
import com.example.apiintegration.ui.theme.ApiIntegrationTheme
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.apiintegration.common.ui.CountryPhoneTextField
import com.example.apiintegration.data.remote.dto.Country


@Composable
fun StartScreen(viewModel: AuthViewModel = hiltViewModel()) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    val profileImageUri by viewModel.profileImage.collectAsState()

    var phone by remember { mutableStateOf("") }
    var selectedCountry by remember {
        mutableStateOf(
            Country("India", "IN", "+91")
        )
    }

    val countries = listOf(
        Country("India", "IN", "+91"),
        Country("Japan", "JP", "+81"),
        Country("China", "CN", "+86")
    )





    Box(modifier = Modifier.fillMaxSize()) {

        // 🎨 Background bubbles (decorative)
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

        // 🧱 Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Create\nAccount",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.height(16.dp))
            ProfileImagePicker(
                imageUri = profileImageUri,
                onImagePicked = viewModel::onProfileImageSelected,
                modifier = Modifier.size(90.dp).align(Alignment.Start)
            )



            Spacer(Modifier.height(24.dp))

            AppOutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = "Username"
            )

            Spacer(Modifier.height(12.dp))

            AppOutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password"
            )

            Spacer(Modifier.height(12.dp))
            CountryPhoneTextField(
                phoneNumber = phone,
                onPhoneChange = { phone = it },
                selectedCountry = selectedCountry,
                onCountrySelected = { selectedCountry = it },
                countries = countries
            )


//            AppOutlinedTextField(
//                value = country,
//                onValueChange = { country = it },
//                label = "Country"
//            )

            Spacer(Modifier.height(24.dp))

            PrimaryButton(
                onClick = { /* submit */ },
                text = "Create Account"
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Cancel",
                modifier = Modifier.clickable { /* navigate back */ },
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAuth() {
    ApiIntegrationTheme {
        StartScreen()
    }
}


