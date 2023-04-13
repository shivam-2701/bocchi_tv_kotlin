package com.example.bocchitv.Repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.bocchitv.Database.AnimeDatabase
import com.example.bocchitv.Database.AnimeHistoryDao
import com.example.bocchitv.Database.AnimeWatchlistDao
import com.example.bocchitv.Database.Entities.AnimeHistory
import com.example.bocchitv.Database.Entities.AnimeWatchList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeRepository(application: Application) {

//    Doa instances
    private lateinit var animeHistoryDao: AnimeHistoryDao
    private lateinit var animeWatchlistDao: AnimeWatchlistDao

//    LiveData Variables
    private lateinit var allAnimeHistory: LiveData<List<AnimeHistory>>
    private lateinit var allAnimeWatchList: LiveData<List<AnimeWatchList>>

    init{

        val database= AnimeDatabase.getInstance(application)

        animeHistoryDao= database.animeHistoryDao()
        animeWatchlistDao= database.animeWatchlistDao()
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

    fun insertAnimeWatchlist(animeWatchList: AnimeWatchList){

        CoroutineScope(Dispatchers.IO).launch {
            animeWatchlistDao.insert(animeWatchList)
        }

    }

    fun updateAnimeHistory(animeWatchList: AnimeWatchList){
        CoroutineScope(Dispatchers.IO).launch {
            animeWatchlistDao.update(animeWatchList)
        }
    }

    fun deleteAnimeHistory(animeWatchList: AnimeWatchList){
        CoroutineScope(Dispatchers.IO).launch {
            animeWatchlistDao.delete(animeWatchList)
        }
    }

    fun getAnimeHistoryList():LiveData<List<AnimeHistory>>{
       return animeHistoryDao.getAll()
    }

    fun getAnimeWatchList():LiveData<List<AnimeWatchList>>{
        return animeWatchlistDao.getAll()
    }




}