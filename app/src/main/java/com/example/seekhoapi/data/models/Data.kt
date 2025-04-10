package com.example.seekhoapi.data.models

data class Data(
    val mal_id: Int,
    val images: Images,
    val trailer: Trailer,
    val title: String,
    val episodes: Int?,
    val rating: String,
    val score: Double?,
    val synopsis: String,
    val genres: List<Genres>
)
