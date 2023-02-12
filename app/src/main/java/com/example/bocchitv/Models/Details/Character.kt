package com.example.bocchitv.Models.Details

data class Character(
    val id: Int? = 0,
    val image: String? = "",
    val name: Name? = Name(),
    val role: String? = "",
    val voiceActors: List<VoiceActor>? = listOf()
)