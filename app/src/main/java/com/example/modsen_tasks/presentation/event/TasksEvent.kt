package com.example.modsen_tasks.presentation.event

sealed interface TasksEvent {
    data object NavigateToLogin : TasksEvent
    data object NavigateToPostList : TasksEvent
    data class ShowErrorMessage(val message: String) : TasksEvent
}


