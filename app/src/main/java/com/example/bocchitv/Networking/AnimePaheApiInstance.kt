package com.example.bocchitv.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object AnimePaheApiInstance {

    lateinit var retrofit: Retrofit
    lateinit var animePaheApi: AnimepaheApi
    val BASE_URL ="https://animepahe.com/"

    init {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        animePaheApi = retrofit.create(AnimepaheApi::class.java)
    }

}