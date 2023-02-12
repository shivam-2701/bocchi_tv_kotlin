package com.example.bocchitv.Models.Main

data class Result(
    val cover: String? = "",
    val description: String? = "",
    val duration: Int? = 0,
    val genres: List<String?>? = listOf(),
    val id: String? = "",
    val image: String? = "",
    val malId: Int? = 0,
    val rating: Int? = 0,
    val releaseDate: Int? = 0,
    val status: String? = "",
    val title: Title? = Title(),
    val totalEpisodes: Int? = 0,
    val trailer: Trailer? = Trailer(),
    val type: String? = ""
)