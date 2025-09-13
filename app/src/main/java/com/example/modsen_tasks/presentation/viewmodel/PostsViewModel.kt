package com.example.modsen_tasks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modsen_tasks.domain.model.PostDomainModel
import com.example.modsen_tasks.domain.usecase.GetPostListUseCase
import com.example.modsen_tasks.presentation.event.PostsEvent
import com.example.modsen_tasks.presentation.event.SingleFlowEvent
import com.example.modsen_tasks.presentation.intent.PostsIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostsViewModel(
    private val getPostListUseCase: GetPostListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostUiState>(PostUiState.LoadingState)
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

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
                    _eventFlow.emit(PostsEvent.ShowMessage("Item ${intent.itemId} was clicked"))
                }
            }
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = PostUiState.LoadingState

            try {
                val posts = getPostListUseCase()

                _uiState.value = PostUiState.LoadedState(posts = posts)

            } catch (e: Exception) {
                _uiState.value = PostUiState.ErrorState(errorMessage = e.message)
                _eventFlow.emit(PostsEvent.ShowMessage(e.message ?: "Login failed"))
            }
        }
    }
}

sealed interface PostUiState {
    data object LoadingState : PostUiState

    data class LoadedState(val posts: List<PostDomainModel>) : PostUiState

    data class ErrorState(val errorMessage: String?) : PostUiState

}