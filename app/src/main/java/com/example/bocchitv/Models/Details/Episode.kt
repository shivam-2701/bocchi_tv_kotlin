package com.example.bocchitv.Models.Details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Episode(
    val description: String? = null,
    val id: String? = null,
    val image: String? = null,
    val number: Int? = null,
    val title: String? = null,
    val url: String? = null
) : Parcelable