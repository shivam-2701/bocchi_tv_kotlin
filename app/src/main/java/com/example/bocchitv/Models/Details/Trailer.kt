package com.example.bocchitv.Models.Details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trailer(
    val id: String? = null,
    val site: String? = null,
    val thumbnail: String? = null
) : Parcelable