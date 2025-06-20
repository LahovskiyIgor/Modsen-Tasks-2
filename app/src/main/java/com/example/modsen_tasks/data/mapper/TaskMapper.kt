package com.example.modsen_tasks.data.mapper

import com.example.modsen_tasks.data.model.TaskDataModel
import com.example.modsen_tasks.domain.model.TaskDomainModel

object TaskMapper {

    fun toDomain(task: TaskDataModel): TaskDomainModel {
        return TaskDomainModel(
            id = task.id,
            name = task.name,
        )
    }
}