package com.example.modsen_tasks.data.exception

sealed class AuthException(message: String) : Exception(message) {
    data object InvalidCredentials : AuthException("Неверный логин или пароль")
}