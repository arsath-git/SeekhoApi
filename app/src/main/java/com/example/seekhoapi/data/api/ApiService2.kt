package com.example.seekhoapi.data.api

import com.example.seekhoapi.data.models.Detail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService2 {
    @GET("anime/{id}")
    suspend fun getAnimeDetail(@Path("id") animeId: Int): Response<Detail>
}