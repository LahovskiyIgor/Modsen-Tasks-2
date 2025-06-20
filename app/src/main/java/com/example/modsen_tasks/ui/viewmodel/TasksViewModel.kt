package com.example.modsen_tasks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks.domain.model.TaskDomainModel
import com.example.modsen_tasks.domain.usecase.GetTasksUseCase
import com.example.modsen_tasks.ui.event.SingleFlowEvent
import com.example.modsen_tasks.ui.event.TasksEvent
import com.example.modsen_tasks.ui.intent.TaskIntent
import com.example.modsen_tasks.ui.state.TasksUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TasksViewModel(
    private val useCase: GetTasksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TasksUiState(isLoading = true))
    val state: StateFlow<TasksUiState> = _uiState

    private val _event = SingleFlowEvent<TasksEvent>(viewModelScope)
    val eventFlow = _event.flow

    init {
        loadTasks()
    }

    fun onIntent(intent: TaskIntent) {
        when (intent) {
            is TaskIntent.LoadTasks -> loadTasks()
            is TaskIntent.TaskItemClicked -> {
                viewModelScope.launch { _event.emit(TasksEvent.NavigateToDetails) }
            }
        }
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _uiState.value = TasksUiState(isLoading = true)

            try {
                val tasks: List<TaskDomainModel> = useCase()

                _uiState.update { it.copy(isLoading = false, tasks = tasks) }


            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
                _event.emit(TasksEvent.ShowErrorMessage(e.message ?: "Login failed"))
            }
        }
    }
}