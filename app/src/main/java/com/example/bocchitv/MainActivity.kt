package com.example.bocchitv

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import com.example.bocchitv.Models.AnimeList
import com.example.bocchitv.Models.RowListItem
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : FragmentActivity() {

    lateinit var txtTitle:TextView
    lateinit var txtSubTitle:TextView
    lateinit var txtDescription: TextView
    lateinit var imgBanner: ImageView
    lateinit var rowListFragment: RowListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgBanner= findViewById(R.id.img_banner)
        txtTitle=findViewById(R.id.title)
        txtDescription=findViewById(R.id.description)
        txtSubTitle=findViewById(R.id.subtitle)


        rowListFragment=RowListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment,rowListFragment)
        transaction.commit()

        val gson = Gson()
        val i : InputStream = this.assets.open("Anime.json")
        val br = BufferedReader(InputStreamReader(i))
//        RowListItem List is created in onder to pass the data to RowListFragment
        val rowListData= ArrayList<RowListItem>()

        val animeListItem:AnimeList= gson.fromJson(br,AnimeList::class.java)
        for(i in 1..3){
            rowListData.add(RowListItem("Recent Anime $i",animeListItem))
        }
        rowListFragment.bindData(rowListData)

    }
}