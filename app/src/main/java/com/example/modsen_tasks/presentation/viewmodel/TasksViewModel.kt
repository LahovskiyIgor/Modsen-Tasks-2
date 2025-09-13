package com.example.modsen_tasks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks.domain.model.TaskDomainModel
import com.example.modsen_tasks.domain.usecase.GetTasksUseCase
import com.example.modsen_tasks.presentation.event.SingleFlowEvent
import com.example.modsen_tasks.presentation.event.TasksEvent
import com.example.modsen_tasks.presentation.intent.TaskIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TasksViewModel(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TasksUiState>(TasksUiState.LoadingState)
    val state: StateFlow<TasksUiState> = _uiState.asStateFlow()

    private val _event = SingleFlowEvent<TasksEvent>(viewModelScope)
    val eventFlow = _event.flow

    init {
        loadTasks()
    }

    fun onIntent(intent: TaskIntent) {
        when (intent) {
            is TaskIntent.LoadTasks -> loadTasks()
            is TaskIntent.TaskItemClicked -> {
                when (intent.itemId) {
                    1 -> viewModelScope.launch { _event.emit(TasksEvent.NavigateToLogin) }
                    2 -> viewModelScope.launch { _event.emit(TasksEvent.NavigateToPostList) }
                }
            }
        }
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _uiState.value = TasksUiState.LoadingState

            try {
                val tasks: List<TaskDomainModel> = getTasksUseCase()
                _uiState.value = TasksUiState.LoadedState(tasks)


            } catch (e: Exception) {
                _uiState.value = TasksUiState.ErrorState(e.message)
                _event.emit(TasksEvent.ShowErrorMessage(e.message ?: "Login failed"))
            }
        }
    }
}

sealed interface TasksUiState {
    data object LoadingState : TasksUiState
    data class LoadedState( val tasks: List<TaskDomainModel>) : TasksUiState
    data class ErrorState (val errorMessage: String? = null) : TasksUiState
}