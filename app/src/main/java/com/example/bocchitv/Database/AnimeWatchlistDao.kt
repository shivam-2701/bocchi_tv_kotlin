package com.example.bocchitv.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bocchitv.Models.Utility.AnimeHistory
import com.example.bocchitv.Models.Utility.AnimeWatchList

@Dao
interface AnimeWatchlistDao {

    @Insert
    suspend fun insert(animeWatchList: AnimeWatchList);

    @Update
    suspend fun update(animeWatchList: AnimeWatchList);

    @Delete
    suspend fun delete(animeWatchList: AnimeWatchList);

    @Query("SELECT * FROM anime_watch_list ORDER BY creationDate DESC")
    fun getAll(): LiveData<List<AnimeWatchList>>
}