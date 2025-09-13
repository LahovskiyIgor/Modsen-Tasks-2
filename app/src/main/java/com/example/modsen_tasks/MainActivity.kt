package com.example.modsen_tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.modsen_tasks.navigation.graph.SetupNavHost
import com.example.modsen_tasks.presentation.theme.ModsenTasksTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModsenTasksTheme {
                val navController = rememberNavController()

                SetupNavHost(navController)
            }
        }
    }
}


