package com.example.modsen_tasks.ui.state

import com.example.modsen_tasks.domain.model.TaskDomainModel
import com.example.modsen_tasks.ui.event.LoginEvent

data class TasksUiState(
    val isLoading: Boolean = false,
    val tasks: List<TaskDomainModel> = emptyList(),
    val errorMessage: String? = null
)