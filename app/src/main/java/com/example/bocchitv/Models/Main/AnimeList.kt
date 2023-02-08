package com.example.bocchitv.Models.Main

data class AnimeList(
    val currentPage: Int,
    val hasNextPage: Boolean,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
)