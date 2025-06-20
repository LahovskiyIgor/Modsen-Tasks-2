package com.example.modsen_tasks.domain.usecase

import com.example.modsen_tasks.domain.model.UserDomainModel
import com.example.modsen_tasks.domain.repository.IAuthRepository

class LoginUseCase(
    private val repository: IAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): UserDomainModel {
        return repository.login(email, password)
    }
}