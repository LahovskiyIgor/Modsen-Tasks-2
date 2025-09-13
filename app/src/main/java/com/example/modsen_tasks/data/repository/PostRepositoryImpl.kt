package com.example.modsen_tasks.data.repository

import com.example.modsen_tasks.data.mapper.mapToEntity
import com.example.modsen_tasks.data.network.ApiService
import com.example.modsen_tasks.domain.model.PostDomainModel
import com.example.modsen_tasks.domain.repository.IPostRepository

class PostRepositoryImpl (val apiService: ApiService) : IPostRepository {


    override suspend fun getAll(): List<PostDomainModel> {
        return apiService.getAllPosts().map { mapToEntity(it) }
    }

    override suspend fun getPostById(id: Int): PostDomainModel {
        return mapToEntity(apiService.getPostById(id))
    }

}