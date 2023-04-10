package com.example.bocchitv.Networking

import com.example.bocchitv.Models.Details.AnimeDetails
import retrofit2.Call
import retrofit2.http.GET
import com.example.bocchitv.Models.Main.AnimeList
import com.example.bocchitv.Models.Utility.SearchAnimeResults
import retrofit2.http.Path

interface AnimeApi {
    @GET("recent?provider=animepahe")
    fun  getRecent(): Call<AnimeList>

    @GET("trending?provider=animepahe")
    fun getTrending(): Call<AnimeList>

    @GET("popular?provider=animepahe")
    fun getPopular():Call<AnimeList>

    @GET("info/{episodeId}?provider=animepahe")
    fun getEpisodeById(@Path("episodeId") episodeId:String):Call<AnimeDetails>

    @GET("/search/{query}")
    suspend fun getSearchResult(@Path("query") query:String): SearchAnimeResults
}