package com.example.apiintegration.presentation.auth

import IntroCard
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.apiintegration.domain.model.Intro.IntroPage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(
    pages: List<IntroPage>,
    onFinish: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { pages.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
