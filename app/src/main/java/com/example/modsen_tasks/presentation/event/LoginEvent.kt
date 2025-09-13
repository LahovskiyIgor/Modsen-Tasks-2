package com.example.modsen_tasks.presentation.event

sealed interface LoginEvent {
    data object NavigateToSuccessScreen : LoginEvent
    data class ShowErrorMessage(val message: String) : LoginEvent
}


