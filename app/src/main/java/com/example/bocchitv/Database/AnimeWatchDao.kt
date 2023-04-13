package com.example.bocchitv.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bocchitv.Database.Entities.AnimeWatch

@Dao
interface AnimeWatchDao {

    @Insert
    suspend fun insert(animeWatch: AnimeWatch);

    @Update
    suspend fun update(animeWatch: AnimeWatch);

    @Delete
    suspend fun delete(animeWatch: AnimeWatch);

    @Query("SELECT * FROM anime_watch_list ORDER BY creationDate DESC")
    fun getAll(): LiveData<List<AnimeWatch>>
}