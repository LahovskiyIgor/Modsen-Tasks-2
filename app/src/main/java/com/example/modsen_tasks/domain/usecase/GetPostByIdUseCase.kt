package com.example.modsen_tasks.domain.usecase

import com.example.modsen_tasks.domain.model.PostDomainModel
import com.example.modsen_tasks.domain.repository.IPostRepository

class GetPostByIdUseCase (
    private val repository: IPostRepository
) {
    suspend operator fun invoke(id: Int) : PostDomainModel {
        return repository.getPostById(id)
    }
}