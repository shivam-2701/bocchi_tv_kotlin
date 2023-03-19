package com.example.bocchitv.Models.Details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TitleXX(
    val english: String? = null,
    val native: String? = null,
    val romaji: String? = null
) : Parcelable