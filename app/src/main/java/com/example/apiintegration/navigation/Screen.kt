package com.example.apiintegration.navigation


sealed class Screen(val route: String) {
    object AppStartScreen : Screen("app_start_screen")
    object IntroScreen : Screen("intro_screen")
    object MainScreen : Screen("main_screen")
    object ProfileScreen : Screen("profile")
    object ProductList : Screen("product")
    object CheckDataScreen : Screen("scr")
    object OtpInputField : Screen("OTP")

    object FetchDataFromRoom:Screen("room")
    object ProductDetailScreen : Screen("product_details/{productId}") {
        fun createRoute(productId: Long) = "product_details/$productId"
    }

    object StartScreen : Screen("start")
    object Auth : Screen("auth")
    object Todo : Screen("todo/{data}") {
        fun createRoute(data: String) = "todo/$data"
    }

    object Home : Screen("home/{username}/{email}") {
        fun createRoute(
            username: String,
            email: String,
        ): String {
            return "home/$username/$email"
        }
    }
}

/*
1. object Home : Screen("home/{username}")
Home is a singleton object in Kotlin.
It extends a class called Screen.
"home/{username}" is a navigation route pattern.
{username} is a placeholder — like a route parameter.
*/

/*
🔹 2. fun createRoute(username: String) = "home/$username"
This function returns the actual route string with the value filled in.
*/

/*
Home.createRoute("Kartik")
returns
home/Kartik
 */

/*You use it when navigating:
navController.navigate(Home.createRoute("Kartik"))*/


