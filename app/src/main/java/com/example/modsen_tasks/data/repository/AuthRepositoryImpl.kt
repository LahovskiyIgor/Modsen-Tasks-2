package com.example.modsen_tasks.data.repository

import com.example.modsen_tasks.data.exception.AuthException
import com.example.modsen_tasks.data.mapper.UserMapper
import com.example.modsen_tasks.data.model.UserDataModel
import com.example.modsen_tasks.domain.model.UserDomainModel
import com.example.modsen_tasks.domain.repository.IAuthRepository
import kotlinx.coroutines.delay

class AuthRepositoryImpl : IAuthRepository {

    private val users: List<UserDataModel> = listOf(
        UserDataModel(1, "Igor", "password"),
        UserDataModel(2, "user", "user_pass"),
        UserDataModel(3, "admin", "admin_pass")
    )

    override suspend fun login(login: String, password: String): UserDomainModel {
        delay(2000)

        return UserMapper.toDomain(
            users.find { it.login == login && it.password == password }
                ?: throw AuthException.InvalidCredentials
        )

    }

}