package com.example.bocchitv.Networking

import com.example.bocchitv.Models.Animepahe.AnimepaheSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AnimepaheApi {

    @GET("api")
    fun fetchAnimePaheSearchList(@QueryMap options:Map<String,String>):Call<AnimepaheSearch>
    @GET("api?m=release&sort=episode_asc")
    fun fetchAnimepaheEpisodes(@Query("id")id:String,@Query("page") page:String):Call<AnimepaheSearch>
    
}