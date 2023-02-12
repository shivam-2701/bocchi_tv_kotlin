package com.example.bocchitv.Models.Details

data class VoiceActor(
    val id: Int? = 0,
    val image: String? = "",
    val language: String? = "",
    val name: Name? = Name()
)