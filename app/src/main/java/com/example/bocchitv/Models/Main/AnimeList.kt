package com.example.bocchitv.Models.Main

data class AnimeList(
    val currentPage: Int? = 0,
    val hasNextPage: Boolean? = false,
    val results: List<Result?>? = listOf()
)