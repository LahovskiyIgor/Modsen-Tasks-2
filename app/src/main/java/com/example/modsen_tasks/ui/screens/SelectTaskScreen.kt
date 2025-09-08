package com.example.modsen_tasks.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.navigation.NavController
import com.example.modsen_tasks.ui.event.TasksEvent
import com.example.modsen_tasks.ui.intent.TaskIntent
import com.example.modsen_tasks.ui.viewmodel.TasksViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SelectTaskScreen(navController: NavController, viewModel: TasksViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is TasksEvent.NavigateToDetails -> {
                    navController.navigate("posts")     //Временно заменил на вкладку с выводом постов
                }

                is TasksEvent.ShowErrorMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when {
        state.isLoading -> CircularProgressIndicator()
        else ->
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = { innerPadding: PaddingValues ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        items(state.tasks) { task ->
                            TaskButton(taskName = task.name)
                            {
                                    viewModel.onIntent(TaskIntent.TaskItemClicked(task.id.toString()))
                            }
                        }
                    }
                }
            )
    }
}

@Composable
fun TaskButton(taskName: String = "Номер задания", onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(taskName)
    }
}