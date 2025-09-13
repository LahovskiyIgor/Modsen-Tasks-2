package com.example.modsen_tasks.presentation.intent

sealed class PostsIntent() {
    data object LoadPosts : PostsIntent()
    data class PostItemClicked(val itemId: Int) : PostsIntent()
}