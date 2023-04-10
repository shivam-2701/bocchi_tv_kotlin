package com.example.bocchitv.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimeApiInstance {
    lateinit var retrofit:Retrofit
    lateinit var animeApi:AnimeApi
    val BASE_URL ="https://relieved-cyan-tuxedo.cyclic.app/"
    init{
        retrofit= Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        animeApi= retrofit.create(AnimeApi::class.java)
    }

}
