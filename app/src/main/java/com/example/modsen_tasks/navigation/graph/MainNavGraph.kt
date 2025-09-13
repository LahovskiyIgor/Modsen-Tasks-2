package com.example.modsen_tasks.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.modsen_tasks.navigation.destinations.NavRoutes
import com.example.modsen_tasks.presentation.screens.login.LoginScreen
import com.example.modsen_tasks.presentation.screens.postList.PostListScreen
import com.example.modsen_tasks.presentation.screens.home.HomeScreen

@Composable
fun SetupNavHost(navController: NavHostController ) {
    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) { HomeScreen(navController) }
        composable(NavRoutes.Login.route) { LoginScreen(navController) }
        composable(NavRoutes.PostList.route) { PostListScreen(navController) }
    }
}