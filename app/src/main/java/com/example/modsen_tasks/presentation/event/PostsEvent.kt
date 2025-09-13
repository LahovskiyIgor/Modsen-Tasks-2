package com.example.modsen_tasks.presentation.event

sealed interface PostsEvent {
    data class ShowMessage(val message: String) : PostsEvent
    data object NavigateToSingleItemScreen : PostsEvent
}