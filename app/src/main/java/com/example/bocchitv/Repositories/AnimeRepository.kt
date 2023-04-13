package com.example.bocchitv.Repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.bocchitv.Database.AnimeDatabase
import com.example.bocchitv.Database.AnimeHistoryDao
import com.example.bocchitv.Database.AnimeWatchDao
import com.example.bocchitv.Database.Entities.AnimeHistory
import com.example.bocchitv.Database.Entities.AnimeWatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeRepository(application: Application) {

//    Doa instances
    private lateinit var animeHistoryDao: AnimeHistoryDao
    private lateinit var animeWatchDao: AnimeWatchDao

//    LiveData Variables
    private lateinit var allAnimeHistory: LiveData<List<AnimeHistory>>
    private lateinit var allAnimeWatch: LiveData<List<AnimeWatch>>

    init{

        val database= AnimeDatabase.getInstance(application)

        animeHistoryDao= database.animeHistoryDao()
        animeWatchDao= database.animeWatchlistDao()
    }

    fun insertAnimeHistory(animeHistory: AnimeHistory){

        CoroutineScope(Dispatchers.IO).launch {
            animeHistoryDao.insert(animeHistory)
        }

    }

    fun updateAnimeHistory(animeHistory: AnimeHistory){
        CoroutineScope(Dispatchers.IO).launch {
            animeHistoryDao.update(animeHistory)
        }
    }

    fun deleteAnimeHistory(animeHistory: AnimeHistory){
        CoroutineScope(Dispatchers.IO).launch {
            animeHistoryDao.delete(animeHistory)
        }
    }

    fun insertAnimeWatchlist(animeWatch: AnimeWatch){

        CoroutineScope(Dispatchers.IO).launch {
            animeWatchDao.insert(animeWatch)
        }

    }

    fun updateAnimeWatchlist(animeWatch: AnimeWatch){
        CoroutineScope(Dispatchers.IO).launch {
            animeWatchDao.update(animeWatch)
        }
    }

    fun deleteAnimeWatchlist(animeWatch: AnimeWatch){
        CoroutineScope(Dispatchers.IO).launch {
            animeWatchDao.delete(animeWatch)
        }
    }

    fun getAnimeHistoryList():LiveData<List<AnimeHistory>>{
       return animeHistoryDao.getAll()
    }

    fun getAnimeWatchList():LiveData<List<AnimeWatch>>{
        return animeWatchDao.getAll()
    }




}