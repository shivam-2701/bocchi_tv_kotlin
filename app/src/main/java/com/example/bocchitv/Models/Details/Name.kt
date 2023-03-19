package com.example.bocchitv.Models.Details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Name(
    val first: String? = null,
    val full: String? = null,
    val last: String? = null,
    val native: String? = null,
    val userPreferred: String? = null
) : Parcelable