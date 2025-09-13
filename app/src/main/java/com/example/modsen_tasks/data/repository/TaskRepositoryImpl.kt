package com.example.modsen_tasks.data.repository

import com.example.modsen_tasks.data.mapper.mapToEntity
import com.example.modsen_tasks.data.model.TaskDataModel
import com.example.modsen_tasks.domain.model.TaskDomainModel
import com.example.modsen_tasks.domain.repository.ITaskRepository

class TaskRepositoryImpl : ITaskRepository {

    private val tasks: List<TaskDataModel> = listOf(
        TaskDataModel(1, "Task 1.1 - Authorization"),
        TaskDataModel(2, "Test Task 1.2 - Retrofit"),
        TaskDataModel(3, "Test Task 1.3"),
    )

    override suspend fun getAll(): List<TaskDomainModel> {
        return tasks.map { mapToEntity(it) }
    }

    override suspend fun getTaskById(id: Int): TaskDomainModel {
        return mapToEntity(
            tasks.find { it.id == id }
                ?: throw NoSuchElementException("TaskRepositoryImpl: element with such id does not exist")
        )
    }

}