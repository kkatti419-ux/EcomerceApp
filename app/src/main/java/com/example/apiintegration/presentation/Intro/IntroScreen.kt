package com.example.apiintegration.presentation.Intro

import IntroCard
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apiintegration.domain.model.Intro.IntroPage
import com.example.apiintegration.presentation.form.FormViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(
    pages: List<IntroPage>,
    onFinish: () -> Unit,
    viewModel: FormViewModel = hiltViewModel() // ✅ ViewModel injected
) {
    val pagerState = rememberPagerState(pageCount = { pages.size })

    // ✅ Collect user credentials from ViewModel
    val credentials by viewModel.credentials.collectAsState()

    // ✅ Local UI state (NO UI IMPACT)
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    // ✅ Load local storage ONCE
    LaunchedEffect(Unit) {
        viewModel.loadUserCredentials()
    }

    // ✅ Update local UI state when data changes
    LaunchedEffect(credentials) {
        credentials?.let {
            firstname = it.firstName ?: ""
            lastname = it.lastName ?: ""
            email = it.email ?: ""
            gender = it.gender ?: ""
            imageUrl = it.profileImage ?: ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(firstname)
        Text(lastname)
        Text(email)
        Text(gender)
        Text(imageUrl)

        Spacer(modifier = Modifier.height(60.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) { page ->
            IntroCard(page = pages[page])
        }



        Spacer(modifier = Modifier.height(24.dp))

        // 🔵 Dots indicator
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pages.size) { index ->
                Box(
                    modifier = Modifier
                        .size(if (pagerState.currentPage == index) 10.dp else 8.dp)
                        .background(
                            if (pagerState.currentPage == index)
                                Color(0xFF2454F8)
                            else
                                Color(0xFFCCD6FF),
                            CircleShape
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 👉 Next / Get Started
        Button(
            onClick = {
                if (pagerState.currentPage == pages.lastIndex) {
                    onFinish()
                }
            },
            enabled = pagerState.currentPage == pages.lastIndex,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = if (pagerState.currentPage == pages.lastIndex)
                    "Get Started"
                else
                    "Swipe"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
