package com.example.bocchitv.Models.Animepahe

data class AnimepaheSearch(
    val current_page: Int? = 0,
    val data: List<Data?>? = listOf(),
    val from: Int? = 0,
    val last_page: Int? = 0,
    val per_page: Int? = 0,
    val to: Int? = 0,
    val total: Int? = 0
)