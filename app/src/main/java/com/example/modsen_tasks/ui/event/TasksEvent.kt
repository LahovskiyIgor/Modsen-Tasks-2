package com.example.modsen_tasks.ui.event

sealed interface TasksEvent {
    data object NavigateToDetails : TasksEvent
    data class ShowErrorMessage(val message: String) : TasksEvent
}


