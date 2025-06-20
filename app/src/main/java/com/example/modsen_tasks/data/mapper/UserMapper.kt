package com.example.modsen_tasks.data.mapper

import com.example.modsen_tasks.data.model.UserDataModel
import com.example.modsen_tasks.domain.model.UserDomainModel

object UserMapper {

    fun toDomain(user: UserDataModel): UserDomainModel {
        return UserDomainModel(
            id = user.id,
            login = user.login,
            password = user.password
        )
    }
}