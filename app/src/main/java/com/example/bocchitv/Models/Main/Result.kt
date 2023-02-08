package com.example.bocchitv.Models.Main

data class Result(
    val color: String,
    val episodeId: String,
    val episodeNumber: Int,
    val episodeTitle: String,
    val genres: List<String>,
    val id: String,
    val image: String,
    val malId: Int,
    val rating: Int,
    val title: Title,
    val type: String
)