package com.example.modsen_tasks.domain.usecase

import com.example.modsen_tasks.domain.model.PostDomainModel
import com.example.modsen_tasks.domain.repository.IPostRepository

class GetPostListUseCase (
    private val repository: IPostRepository
) {
    suspend operator fun invoke() : List<PostDomainModel> {
        return repository.getAll()
    }
}