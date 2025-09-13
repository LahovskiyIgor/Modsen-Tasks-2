package com.example.modsen_tasks.presentation.intent

sealed class TaskIntent() {
    data object LoadTasks : TaskIntent()
    data class TaskItemClicked(val itemId: Int) : TaskIntent()
}