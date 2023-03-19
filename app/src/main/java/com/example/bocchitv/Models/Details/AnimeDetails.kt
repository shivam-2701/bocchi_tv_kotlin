package com.example.bocchitv.Models.Details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeDetails(
    val characters: List<Character>? = listOf(),
    val color: String? = "",
    val countryOfOrigin: String? = "",
    val cover: String? = "",
    val currentEpisode: Int? = 0,
    val description: String? = "",
    val duration: Int? = 0,
    val endDate: EndDate? = EndDate(),
    val episodes: List<Episode>? = listOf(),
    val genres: List<String>? = listOf(),
    val id: String? = "",
    val image: String? = "",
    val isAdult: Boolean? = false,
    val isLicensed: Boolean? = false,
    val malId: Int? = 0,
    val popularity: Int? = 0,
    val rating: Int? = 0,
    val recommendations: List<Recommendation>? = listOf(),
    val relations: List<Relation>? = listOf(),
    val releaseDate: Int? = 0,
    val season: String? = "",
    val startDate: StartDate? = StartDate(),
    val status: String? = "",
    val studios: List<String>? = listOf(),
    val subOrDub: String? = "",
    val synonyms: List<String>? = listOf(),
    val title: TitleXX? = TitleXX(),
    val totalEpisodes: Int? = 0,
    val trailer: Trailer? = Trailer(),
    val type: String? = ""
) : Parcelable