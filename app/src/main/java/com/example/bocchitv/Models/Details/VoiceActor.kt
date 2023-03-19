package com.example.bocchitv.Models.Details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VoiceActor(
    val id: Int? = 0,
    val image: String? = "",
    val language: String? = "",
    val name: Name? = Name()
) : Parcelable