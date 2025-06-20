package com.example.modsen_tasks.domain.repository

import com.example.modsen_tasks.domain.model.UserDomainModel

interface IAuthRepository {
    suspend fun login(login: String, password: String): UserDomainModel

}