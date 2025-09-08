package com.example.modsen_tasks.domain.model

data class PostDomainModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)