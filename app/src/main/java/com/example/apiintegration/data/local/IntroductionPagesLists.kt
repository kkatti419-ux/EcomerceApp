package com.example.apiintegration.data.local

import com.example.apiintegration.domain.model.Intro.IntroPage
import com.example.apiintegration.R


object IntroductionPagesLists {
    val introPages = listOf(
        IntroPage(
            imageRes = R.drawable.bubble_2,
            title = "Hello",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        ),
        IntroPage(
            imageRes = R.drawable.bubble_2,
            title = "Shop Easily",
            description = "Browse and shop your favorite products easily."
        ),
        IntroPage(
            imageRes = R.drawable.bubble_2,
            title = "Fast Delivery",
            description = "Get your orders delivered at lightning speed."
        )
    )

}