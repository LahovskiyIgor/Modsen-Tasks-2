package com.example.modsen_tasks.di.module

import com.example.modsen_tasks.data.repository.AuthRepositoryImpl
import com.example.modsen_tasks.data.repository.PostRepositoryImpl
import com.example.modsen_tasks.data.repository.TaskRepositoryImpl
import com.example.modsen_tasks.domain.repository.IAuthRepository
import com.example.modsen_tasks.domain.repository.IPostRepository
import com.example.modsen_tasks.domain.repository.ITaskRepository
import org.koin.dsl.module

val dataModule = module {
    single<IAuthRepository> {
        AuthRepositoryImpl()
    }

    single<ITaskRepository> {
        TaskRepositoryImpl()
    }

    single<IPostRepository> {
        PostRepositoryImpl(apiService = get())
    }
}