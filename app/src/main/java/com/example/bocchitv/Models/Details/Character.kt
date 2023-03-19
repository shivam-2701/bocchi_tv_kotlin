package com.example.bocchitv.Models.Details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int? = 0,
    val image: String? = "",
    val name: Name? = Name(),
    val role: String? = "",
    val voiceActors: List<VoiceActor>? = listOf()
) : Parcelable