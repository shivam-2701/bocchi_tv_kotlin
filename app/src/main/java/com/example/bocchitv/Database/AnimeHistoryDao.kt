package com.example.bocchitv.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bocchitv.Database.Entities.AnimeHistory

@Dao
interface AnimeHistoryDao {

    @Insert
    suspend fun insert(animeHistory: AnimeHistory);

    @Update
    suspend fun update(animeHistory: AnimeHistory);

    @Delete
    suspend fun delete(animeHistory: AnimeHistory);

    @Query("SELECT * FROM anime_watch_history ORDER BY creationDate DESC")
    fun getAll():LiveData<List<AnimeHistory>>

}