package com.example.modsen_tasks.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.modsen_tasks.presentation.event.LoginEvent
import com.example.modsen_tasks.presentation.intent.LoginIntent
import com.example.modsen_tasks.presentation.viewmodel.LoginUiState
import com.example.modsen_tasks.presentation.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is LoginEvent.NavigateToSuccessScreen -> {
                    navController.navigate("empty")
                }

                is LoginEvent.ShowErrorMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding: PaddingValues ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Welcome!", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(48.dp))
                TextField("Login", uiState.login) {
                    viewModel.onIntent(LoginIntent.EnterEmail(it))

                }
                Spacer(modifier = Modifier.height(24.dp))
                TextField("Password", value = uiState.password) {
                    viewModel.onIntent(LoginIntent.EnterPassword(it))
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { viewModel.onIntent(LoginIntent.SubmitLogin) },
                    enabled = uiState.isButtonEnabled
                ) {
                    Text(
                        "Sign in",
                        modifier = Modifier.padding(16.dp, 4.dp)
                    )
                }
            }
        }
    )
}





