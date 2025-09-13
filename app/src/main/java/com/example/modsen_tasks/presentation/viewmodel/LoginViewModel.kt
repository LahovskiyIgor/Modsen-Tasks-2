package com.example.modsen_tasks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks.domain.usecase.LoginUseCase
import com.example.modsen_tasks.presentation.event.SingleFlowEvent
import com.example.modsen_tasks.presentation.event.LoginEvent
import com.example.modsen_tasks.presentation.intent.LoginIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState(isLoading = true))
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _event = SingleFlowEvent<LoginEvent>(viewModelScope)
    val eventFlow = _event.flow
    
    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EnterEmail -> {
                _uiState.update { it.copy(login = intent.login) }
            }

            is LoginIntent.EnterPassword -> {
                _uiState.update { it.copy(password = intent.password) }
            }

            LoginIntent.SubmitLogin -> {
                login()
            }
        }
    }

    private fun login() {

        val current = _uiState.value
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                loginUseCase(current.login, current.password)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                    )
                }
                _event.emit(LoginEvent.NavigateToSuccessScreen)

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
                _event.emit(LoginEvent.ShowErrorMessage(e.message ?: "Login failed"))
            }
        }
    }
}

data class LoginUiState(
    val isLoading: Boolean = false,
    val login: String = "",
    val password: String = "",
    val errorMessage: String? = null
)
{
    val isButtonEnabled: Boolean get() = login.isNotBlank() && password.isNotBlank()
}