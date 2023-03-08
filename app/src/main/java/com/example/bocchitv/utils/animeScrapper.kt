package com.example.bocchitv.utils

import com.example.bocchitv.Models.Animepahe.AnimepaheSearch
import retrofit2.http.GET
import retrofit2.http.HTTP

fun getAnimepaheId(query:String , releasedYear:String, season:String="unknown",fillData:(result:AnimepaheSearch)->Unit){

}