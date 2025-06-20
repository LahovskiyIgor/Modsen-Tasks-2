package com.example.modsen_tasks.domain.repository

import com.example.modsen_tasks.domain.model.TaskDomainModel

interface ITaskRepository {
    suspend fun getAll(): List<TaskDomainModel>
    suspend fun getTaskById(id: Int): TaskDomainModel
}