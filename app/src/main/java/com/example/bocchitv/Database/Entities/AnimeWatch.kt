package com.example.bocchitv.Database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bocchitv.Models.Details.TitleXX
import java.sql.Date

@Entity(tableName = "anime_watch_list")
class AnimeWatch(val title: TitleXX? = TitleXX(),
                 val season: String? = "",
                 val cover: String? = "",
                 val malId: Int? = 0,
                 val image: String? = "",
                 val releaseDate: Int? = 0,
                 val creationDate: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int=0

}