package com.example.modsen_tasks.ui.event

sealed interface PostsEvent {
    data class ShowMessage(val message: String) : PostsEvent
    data object NavigateToPreviousScreen : PostsEvent
    data object NavigateToSingleItemScreen : PostsEvent
}