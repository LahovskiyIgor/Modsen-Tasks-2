package com.example.modsen_tasks.ui.state

data class LoginUiState(
    val isLoading: Boolean = false,
    val login: String = "",
    val password: String = "",
    val errorMessage: String? = null,
)
{
    val isButtonEnabled: Boolean get() = login.isNotBlank() && password.isNotBlank()
}