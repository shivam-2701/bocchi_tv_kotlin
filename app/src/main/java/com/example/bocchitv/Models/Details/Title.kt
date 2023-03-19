package com.example.bocchitv.Models.Details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Title(
    val english: String? = null,
    val native: String? = null,
    val romaji: String? = null,
    val userPreferred: String? = null
) : Parcelable