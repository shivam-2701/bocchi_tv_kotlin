package com.example.bocchitv.Models

import android.os.Parcelable
import com.example.bocchitv.Models.Details.AnimeDetails
import com.example.bocchitv.Models.Details.TitleXX
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaInfo(val title: TitleXX? = TitleXX(),val season: String? = "", val cover: String? = "",val malId: Int? = 0,val image: String? = "",val releaseDate: Int? = 0) :
    Parcelable {
    constructor(animeDetails:AnimeDetails) : this(animeDetails.title,animeDetails.season,animeDetails.cover,animeDetails.malId,animeDetails.image,animeDetails.releaseDate) {

    }
}
