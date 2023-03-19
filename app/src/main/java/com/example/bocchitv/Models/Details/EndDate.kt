package com.example.bocchitv.Models.Details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EndDate(
    val day: Int? = null,
    val month: Int? = null,
    val year: Int? = null
) : Parcelable