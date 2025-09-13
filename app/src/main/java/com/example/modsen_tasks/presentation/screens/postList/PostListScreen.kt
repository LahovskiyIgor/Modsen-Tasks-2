package com.example.modsen_tasks.presentation.screens.postList

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.modsen_tasks.presentation.event.PostsEvent
import com.example.modsen_tasks.presentation.intent.PostsIntent
import com.example.modsen_tasks.presentation.viewmodel.PostUiState
import com.example.modsen_tasks.presentation.viewmodel.PostsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostListScreen(navController: NavController, viewModel: PostsViewModel = koinViewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when(event) {
                is PostsEvent.NavigateToSingleItemScreen -> {
                    Log.d("DEBUG", "NAVIGATE TO SINGLE ITEM SCREEN")
                }
                is PostsEvent.ShowMessage -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()

            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = uiState) {
            is PostUiState.LoadedState -> {
                LazyColumn(
                    modifier = Modifier.padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.posts) { post ->
                        PostItem(
                            id = post.id,
                            title = post.title,
                            body = post.body
                        ) {
                            viewModel.onIntent(PostsIntent.PostItemClicked(itemId = post.id))
                        }
                    }
                }
            }
            PostUiState.LoadingState -> CircularProgressIndicator()
            is PostUiState.ErrorState -> Text("Ошибка: ${state.errorMessage}")
        }
    }
}

