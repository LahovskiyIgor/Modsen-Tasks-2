package com.example.modsen_tasks.data.mapper

import com.example.modsen_tasks.data.model.TaskDataModel
import com.example.modsen_tasks.domain.model.TaskDomainModel


fun mapToEntity(task: TaskDataModel): TaskDomainModel {
    return TaskDomainModel(
        id = task.id,
        name = task.name,
    )
}
