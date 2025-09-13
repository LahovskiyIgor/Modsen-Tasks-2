package com.example.modsen_tasks.presentation.intent

sealed class LoginIntent() {
    data class EnterEmail(val login: String) : LoginIntent()
    data class EnterPassword(val password: String) : LoginIntent()
    data object SubmitLogin : LoginIntent()
}