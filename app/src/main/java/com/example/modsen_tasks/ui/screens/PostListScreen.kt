package com.example.modsen_tasks.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.modsen_tasks.ui.event.PostsEvent
import com.example.modsen_tasks.ui.viewmodel.PostsViewModel
import org.koin.androidx.compose.koinViewModel
import java.nio.file.WatchEvent
import kotlin.math.log

@Composable
fun PostListScreen(navController: NavController, viewModel: PostsViewModel = koinViewModel()) {

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when(event) {
                is PostsEvent.NavigateToPreviousScreen ->
                    navController.navigate("home")
                is PostsEvent.NavigateToSingleItemScreen -> {
                    Log.d("DEBUG", "NAVIGATE TO SINGLE ITEM SCREEN")
                }
                is PostsEvent.ShowMessage -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()

            }
        }
    }

    when {
        state.isLoading -> CircularProgressIndicator()
        else -> Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
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

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PostItem(
    id: Int = 1,
    title: String = "This is title",
    body: String = "Editing Git commits involves modifying either the commit message, the content of the commit, or both. The method used depends on whether the commit is the most recent one or an older commit in the history.",
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .padding(0.dp)
            .background(color = Color.White, shape = RoundedCornerShape(20))
            .padding(8.dp)
            .clickable(true, onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,


    ) {
        Box(
            modifier = Modifier
                .width(36.dp)
                .height(36.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Color(0xff0047ab), Color(0xff1ca9c9))),
                    shape = RoundedCornerShape(20)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                text = id.toString()
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(0.dp, 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
            Text(text = body.slice(0..80) + "...")
        }

    }
}