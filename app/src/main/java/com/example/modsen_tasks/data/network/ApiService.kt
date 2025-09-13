package com.example.modsen_tasks.data.network

import com.example.modsen_tasks.data.model.PostDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/posts")
    suspend fun getAllPosts() : List<PostDataModel>

    @GET("/posts")
    suspend fun getPostById(@Query("id") id: Int) : PostDataModel

}