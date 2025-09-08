package com.example.modsen_tasks.data.mapper

import com.example.modsen_tasks.data.model.PostDataModel
import com.example.modsen_tasks.domain.model.PostDomainModel
import com.example.modsen_tasks.domain.model.TaskDomainModel

object PostMapper {

    fun toDomain(post: PostDataModel): PostDomainModel {
        return PostDomainModel(
            id = post.id,
            userId = post.userId,
            title = post.title,
            body = post.body
        )
    }
}