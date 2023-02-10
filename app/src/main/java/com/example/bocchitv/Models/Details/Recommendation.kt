package com.example.bocchitv.Models.Details

data class Recommendation(
    val cover: String,
    val episodes: Int,
    val id: Int,
    val image: String,
    val malId: Int,
    val rating: Int,
    val status: String,
    val title: Title,
    val type: String
)