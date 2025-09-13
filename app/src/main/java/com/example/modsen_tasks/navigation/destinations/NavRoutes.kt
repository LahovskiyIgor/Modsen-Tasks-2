package com.example.modsen_tasks.navigation.destinations

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Login : NavRoutes("login")
    object PostList : NavRoutes("posts")
}