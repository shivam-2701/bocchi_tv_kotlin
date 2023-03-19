package com.example.bocchitv.Models.Details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recommendation(
    val cover: String? = null,
    val episodes: Int? = null,
    val id: Int? = null,
    val image: String? = null,
    val malId: Int? = null,
    val rating: Int? = null,
    val status: String? = null,
    val title: Title? = null,
    val type: String? = null
) : Parcelable