package com.example.apiintegration.navigation


sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Todo : Screen("todo/{data}") {
        fun createRoute(data: String) = "todo/$data"
    }

    object Home : Screen("home/{username}") {
        fun createRoute(username: String) = "home/$username"/*
               fun createRoute(username: String) = "home/$username"
               Kotlin sees "home/$username" is a String, so it infers that the return type is: String        🟩 Without Inference (Explicit)
               fun createRoute(username: String): String {
                   return "home/$username"
                }
         */

        fun validateInput(text: String): Unit {
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


