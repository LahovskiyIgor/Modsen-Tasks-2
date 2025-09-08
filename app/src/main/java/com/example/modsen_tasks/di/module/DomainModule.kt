package com.example.modsen_tasks.di.module

import com.example.modsen_tasks.domain.usecase.GetPostByIdUseCase
import com.example.modsen_tasks.domain.usecase.GetPostListUseCase
import com.example.modsen_tasks.domain.usecase.GetTasksUseCase
import com.example.modsen_tasks.domain.usecase.LoginUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<LoginUseCase> {
        LoginUseCase(repository = get())
    }

    factory<GetTasksUseCase> {
        GetTasksUseCase(repository = get())
    }

    factory<GetPostListUseCase> {
        GetPostListUseCase(repository = get())
    }

    factory<GetPostByIdUseCase> {
        GetPostByIdUseCase(repository = get())
    }

}