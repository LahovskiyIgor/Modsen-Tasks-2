package com.example.modsen_tasks.ui.event

sealed interface LoginEvent {
    data object NavigateToSuccessScreen : LoginEvent
    data class ShowErrorMessage(val message: String) : LoginEvent
}


