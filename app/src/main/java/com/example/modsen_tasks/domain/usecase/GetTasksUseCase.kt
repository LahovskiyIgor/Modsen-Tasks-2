package com.example.modsen_tasks.domain.usecase

import com.example.modsen_tasks.domain.model.TaskDomainModel
import com.example.modsen_tasks.domain.repository.ITaskRepository

class GetTasksUseCase(
    private val repository: ITaskRepository
) {
    suspend operator fun invoke(): List<TaskDomainModel> {
        return repository.getAll()
    }
}