package com.example.modsen_tasks.data.repository

import com.example.modsen_tasks.data.mapper.PostMapper
import com.example.modsen_tasks.data.mapper.TaskMapper
import com.example.modsen_tasks.data.model.PostDataModel
import com.example.modsen_tasks.data.model.TaskDataModel
import com.example.modsen_tasks.data.network.ApiService
import com.example.modsen_tasks.domain.model.PostDomainModel
import com.example.modsen_tasks.domain.model.TaskDomainModel
import com.example.modsen_tasks.domain.repository.IPostRepository
import com.example.modsen_tasks.domain.repository.ITaskRepository

class PostRepositoryImpl (val apiService: ApiService) : IPostRepository {


    override suspend fun getAll(): List<PostDomainModel> {
        return apiService.getAllPosts().map { PostMapper.toDomain(it) }
    }

    override suspend fun getPostById(id: Int): PostDomainModel {
        return PostMapper.toDomain(
            apiService.getPostById(id)
        )
    }

}