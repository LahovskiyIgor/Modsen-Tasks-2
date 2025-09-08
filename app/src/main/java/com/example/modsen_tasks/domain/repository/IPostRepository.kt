package com.example.modsen_tasks.domain.repository

import com.example.modsen_tasks.domain.model.PostDomainModel
import com.example.modsen_tasks.domain.model.UserDomainModel

interface IPostRepository {
    suspend fun getAll() : List<PostDomainModel>

    suspend fun getPostById(id: Int) : PostDomainModel

}