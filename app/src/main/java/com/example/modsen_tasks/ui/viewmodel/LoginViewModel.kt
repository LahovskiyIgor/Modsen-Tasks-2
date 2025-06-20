package com.example.modsen_tasks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks.domain.usecase.LoginUseCase
import com.example.modsen_tasks.ui.event.SingleFlowEvent
import com.example.modsen_tasks.ui.event.LoginEvent
import com.example.modsen_tasks.ui.intent.LoginIntent
import com.example.modsen_tasks.ui.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

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
                val user = loginUseCase(current.login, current.password)

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