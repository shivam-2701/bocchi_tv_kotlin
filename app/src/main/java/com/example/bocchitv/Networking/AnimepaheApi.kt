package com.example.bocchitv.Networking

import com.example.bocchitv.Models.Animepahe.AnimepaheSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface AnimepaheApi {

    @GET("api")
    fun getAnimepaheList(@QueryMap options:Map<String,String>):Call<AnimepaheSearch>

}