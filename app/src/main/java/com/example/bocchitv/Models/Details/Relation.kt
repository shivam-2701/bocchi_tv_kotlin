package com.example.bocchitv.Models.Details

data class Relation(
    val color: String? = "",
    val cover: String? = "",
    val episodes: Int? = 0,
    val id: Int? = 0,
    val image: String? = "",
    val malId: Int? = 0,
    val rating: Int? = 0,
    val relationType: String? = "",
    val status: String? = "",
    val title: TitleXX? = TitleXX(),
    val type: String? = ""
)