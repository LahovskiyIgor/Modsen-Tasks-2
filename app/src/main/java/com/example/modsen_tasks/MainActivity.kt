package com.example.modsen_tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modsen_tasks.ui.screens.LoginScreen
import com.example.modsen_tasks.ui.screens.SelectTaskScreen
import com.example.modsen_tasks.ui.theme.ModsenTasksTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModsenTasksTheme {
                val navController = rememberNavController();

                NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
                    composable(NavRoutes.Home.route) { SelectTaskScreen(navController) }
                    composable(NavRoutes.Login.route) { LoginScreen(navController) }
                    composable(NavRoutes.Empty.route) { SelectTaskScreen(navController) }
                }
            }
        }
    }
}


sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Login : NavRoutes("login")
    object Empty : NavRoutes("empty")
}