package com.example.bocchitv.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.bocchitv.Database.Entities.AnimeHistory
import com.example.bocchitv.Database.Entities.AnimeWatch
import com.example.bocchitv.Repositories.AnimeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var repository: AnimeRepository
    private lateinit var animeWatchList: LiveData<List<AnimeWatch>>
    private lateinit var allAnimeHistory: LiveData<List<AnimeHistory>>

    init {
        repository = AnimeRepository(application)
        animeWatchList = repository.getAnimeWatchList()
        allAnimeHistory = repository.getAnimeHistoryList()
    }

    fun insertAnimeHistory(animeHistory: AnimeHistory){

        repository.insertAnimeHistory(animeHistory)

    }

    fun updateAnimeHistory(animeHistory: AnimeHistory){
        repository.updateAnimeHistory(animeHistory)
    }

    fun deleteAnimeHistory(animeHistory: AnimeHistory){
        repository.deleteAnimeHistory(animeHistory)
    }

    fun insertAnimeWatchlist(animeWatch: AnimeWatch){

        repository.insertAnimeWatchlist(animeWatch)

    }

    fun updateAnimeWatchlist(animeWatch: AnimeWatch){
        repository.updateAnimeWatchlist(animeWatch)
    }
    fun deleteAnimeHistory(animeWatch: AnimeWatch){
        repository.deleteAnimeWatchlist(animeWatch)
    }

    fun getAnimeHistoryList():LiveData<List<AnimeHistory>>{
        return allAnimeHistory
    }

    fun getAnimeWatchList():LiveData<List<AnimeWatch>>{
        return animeWatchList
    }


}