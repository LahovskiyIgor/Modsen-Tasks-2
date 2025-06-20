package com.example.modsen_tasks.ui.intent

sealed class TaskIntent() {
    data object LoadTasks : TaskIntent()
    data class TaskItemClicked(val itemId: String) : TaskIntent()
}