package com.example.bocchitv.Models

data class SearchAnimeResults(
    val currentPage: Int? = null,
    val hasNextPage: Boolean? = null,
    val results: List<Result?>? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null
) {
    data class Result(
        val color: String? = null,
        val cover: String? = null,
        val description: String? = null,
        val genres: List<String?>? = null,
        val id: String? = null,
        val image: String? = null,
        val malId: Int? = null,
        val popularity: Int? = null,
        val rating: Int? = null,
        val releaseDate: Int? = null,
        val status: String? = null,
        val title: Title? = null,
        val totalEpisodes: Int? = null,
        val type: String? = null
    ) {
        data class Title(
            val english: String? = null,
            val native: String? = null,
            val romaji: String? = null,
            val userPreferred: String? = null
        )
    }
}