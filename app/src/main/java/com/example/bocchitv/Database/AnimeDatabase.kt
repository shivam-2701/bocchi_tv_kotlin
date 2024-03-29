package com.example.bocchitv.Database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bocchitv.Database.Entities.AnimeHistory
import com.example.bocchitv.Database.Entities.AnimeWatch

@Database(entities = [AnimeHistory::class, AnimeWatch::class],version=1)
abstract class AnimeDatabase:RoomDatabase() {

    abstract fun animeHistoryDao():AnimeHistoryDao
    abstract fun animeWatchlistDao():AnimeWatchDao

    companion object{

        private var instance:AnimeDatabase?= null

        fun getInstance(context:Context):AnimeDatabase{
            if(instance==null){

                instance = Room.databaseBuilder(context.applicationContext,
                AnimeDatabase::class.java,"anime_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            }
            return instance!!
        }
        private var roomCallback:RoomDatabase.Callback =object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d("ROOM DATABASE","New DB created");
            }
        }

    }

}