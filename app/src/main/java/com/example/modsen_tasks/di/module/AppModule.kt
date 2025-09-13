package com.example.modsen_tasks.di.module

import com.example.modsen_tasks.presentation.viewmodel.LoginViewModel
import com.example.modsen_tasks.presentation.viewmodel.PostsViewModel
import com.example.modsen_tasks.presentation.viewmodel.TasksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<LoginViewModel> {
        LoginViewModel(loginUseCase = get() )
    }

    viewModel<TasksViewModel> {
        TasksViewModel(getTasksUseCase = get() )
    }

    viewModel<PostsViewModel> {
        PostsViewModel(getPostListUseCase = get() )
    }
}