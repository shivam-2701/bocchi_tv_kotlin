package com.example.bocchitv.utils

import com.example.bocchitv.Models.Animepahe.AnimePaheEpisosdeResult
import com.example.bocchitv.Models.Animepahe.AnimepaheSearch
import com.example.bocchitv.Models.Details.AnimeDetails

val baseUrl = "https://relieved-cyan-tuxedo.cyclic.app"
fun getEpisodeList(title:String, releasedYear:Int,season:String,page:Int):Pair<String,AnimePaheEpisosdeResult>{

    try{
        val animeId = getAnimepaheId(title,releasedYear.toString(),season);
        val episodeList = fetchAnimePaheEpisodes(animeId,page);
        return Pair(animeId,episodeList)

    }catch (error:Exception){
        throw ApiException(message="Failed to load episode info.", cause = error.cause!!)
    }
}

