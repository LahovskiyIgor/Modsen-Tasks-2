package com.example.modsen_tasks.ui.intent

sealed class PostsIntent() {
    data object LoadPosts : PostsIntent()
    data class PostItemClicked(val itemId: String) : PostsIntent()
}