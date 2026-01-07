package com.example.apiintegration.data.local.datasource

import com.example.apiintegration.R
import com.example.apiintegration.domain.model.Intro.IntroPage

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