package com.example.digii.data.remote

import com.example.digii.data.remote.model.PostModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiPostService {
    @GET("post")
    suspend fun getPosts(): Response<PostModel>
}