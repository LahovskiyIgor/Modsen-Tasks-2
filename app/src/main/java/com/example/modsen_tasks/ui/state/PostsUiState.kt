package com.example.modsen_tasks.ui.state

import com.example.modsen_tasks.domain.model.PostDomainModel

data class PostsUiState (
    val isLoading: Boolean = false,
    val posts: List<PostDomainModel> = emptyList(),
    val errorMessage: String? = null
)