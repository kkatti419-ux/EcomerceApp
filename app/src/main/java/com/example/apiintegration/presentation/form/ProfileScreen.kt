package com.example.apiintegration.presentation.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.apiintegration.R
import com.example.apiintegration.common.ui.AppOutlinedTextField
import com.example.apiintegration.common.ui.PrimaryButton
import com.example.apiintegration.common.utils.AppLogger
import com.example.apiintegration.ui.theme.ApiIntegrationTheme

@Composable
fun ProfileScreenContent(
    firstname: String,
    lastname: String,
    email: String,
    gender: String,
    imageUrl: String,
    selectedUserId: Int?,
    onFirstnameChange: (String) -> Unit,
    onLastnameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onGenderChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCancelEdit: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileAvatar(imageUrl = imageUrl)

        AppOutlinedTextField(
            value = firstname,
            onValueChange = onFirstnameChange,
            label = "Firstname"
        )

        AppOutlinedTextField(
            value = lastname,
            onValueChange = onLastnameChange,
            label = "Lastname"
        )

        AppOutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email"
        )

        AppOutlinedTextField(
            value = gender,
            onValueChange = onGenderChange,
            label = "Gender"
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            onClick = onSaveClick,
            text = "Update User"
        )
    }
}


@Composable
fun ProfileScreen(
    viewModel: FormViewModel = hiltViewModel(),
) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var selectedUserId by remember { mutableStateOf<Int?>(null) }

    val credentials by viewModel.credentials.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUserCredentials()
    }
    LaunchedEffect(credentials) {
        credentials?.let {
            firstname = it.firstName ?: ""
            lastname = it.lastName ?: ""
            email = it.email ?: ""
            gender = it.gender ?: ""
            imageUrl = it.profileImage ?: ""
        }
    }

    AppLogger.d("ProfileScreen", "firstname: $firstname, lastname: $lastname")
    AppLogger.d("ProfileScreen", "selectedUserId: $selectedUserId")
    AppLogger.d("ProfileScreen", "imageUrl: $imageUrl")
    AppLogger.d("ProfileScreen", "email: $email")
    AppLogger.d("ProfileScreen", "gender: $gender")







    ProfileScreenContent(
        firstname = firstname,
        lastname = lastname,
        email = email,
        gender = gender,
        imageUrl = imageUrl,
        selectedUserId = selectedUserId,
        onFirstnameChange = { firstname = it },
        onLastnameChange = { lastname = it },
        onEmailChange = { email = it },
        onGenderChange = { gender = it },
        onSaveClick = {
            viewModel.updateUserCredentials(
                username = credentials?.username.orEmpty(),
                firstname = firstname,
                lastname = lastname,
                phone = credentials?.phone.orEmpty(),
                email = email,
                profileImage = imageUrl,
                gender = gender
            )
        },
        onCancelEdit = {}
    )

}


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ApiIntegrationTheme {
        ProfileScreenContent(
            firstname = "John",
            lastname = "Doe",
            gender = "Male",
            email = "abc@Gmail.com",
            imageUrl = "",
            selectedUserId = null,
            onFirstnameChange = {},
            onLastnameChange = {},
            onEmailChange = {},
            onGenderChange = {},
            onSaveClick = {},
            onCancelEdit = {}
        )
    }
}


@Composable
fun ProfileAvatar(
    imageUrl: String?,
    size: Dp = 100.dp,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true).build()
    )

    Image(
        painter = painter,
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(2.dp, Color.LightGray, CircleShape),
        contentScale = ContentScale.Crop
    )
}
