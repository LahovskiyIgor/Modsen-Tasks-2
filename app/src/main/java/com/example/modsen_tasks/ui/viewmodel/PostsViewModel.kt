package com.example.modsen_tasks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks.domain.usecase.GetPostListUseCase
import com.example.modsen_tasks.ui.event.PostsEvent
import com.example.modsen_tasks.ui.event.SingleFlowEvent
import com.example.modsen_tasks.ui.intent.PostsIntent
import com.example.modsen_tasks.ui.state.PostsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel(
    private val getPostListUseCase: GetPostListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostsUiState(isLoading = true))
    val uiState = _uiState

    private val _eventFlow = SingleFlowEvent<PostsEvent>(viewModelScope)
    val eventFlow = _eventFlow.flow

    init {
        loadPosts()
    }

    fun onIntent(intent: PostsIntent) {
        when (intent) {
            is PostsIntent.LoadPosts -> {
                loadPosts()
            }
            is PostsIntent.PostItemClicked -> {
                viewModelScope.launch {
                    _eventFlow.emit(PostsEvent.ShowMessage("item clicked"))
                }
            }
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = PostsUiState(isLoading = true)

            try {
                val posts = getPostListUseCase()

                _uiState.update { it.copy(isLoading = false, posts = posts) }

            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
                _eventFlow.emit(PostsEvent.ShowMessage(e.message ?: "Login failed"))
            }
        }




    }

}