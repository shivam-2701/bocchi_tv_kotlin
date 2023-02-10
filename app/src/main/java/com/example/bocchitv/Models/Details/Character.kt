package com.example.bocchitv.Models.Details

data class Character(
    val id: Int,
    val image: String,
    val name: Name,
    val role: String,
    val voiceActors: List<VoiceActor>
)