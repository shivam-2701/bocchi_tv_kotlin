package com.example.bocchitv.Networking

import retrofit2.Call
import retrofit2.http.GET
import com.example.bocchitv.Models.AnimeList

interface AnimeApi {
    @GET("recent-episodes?provider=animepahe")
    fun  getRecent(): Call<AnimeList>
}