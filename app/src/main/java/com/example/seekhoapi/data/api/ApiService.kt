package com.example.seekhoapi.data.api

import com.example.seekhoapi.data.models.Post
import retrofit2.http.GET

interface ApiService {
    @GET("top/anime")
    suspend fun getPosts(): Post
}