package com.example.bocchitv

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.bocchitv.Models.AnimeList
import com.example.bocchitv.Models.RowListItem
import com.google.gson.Gson
import java.io.BufferedReader
import com.example.bocchitv.Models.Result
import com.example.bocchitv.Networking.AnimeApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.io.InputStreamReader


class MainActivity : FragmentActivity() {

    lateinit var txtTitle: TextView
    lateinit var txtSubTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var imgBanner: ImageView
    lateinit var rowListFragment: RowListFragment
    var animeListItem: AnimeList?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgBanner = findViewById(R.id.img_banner)
        txtTitle = findViewById(R.id.title)
        txtDescription = findViewById(R.id.description)
        txtSubTitle = findViewById(R.id.subtitle)


        rowListFragment = RowListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, rowListFragment)
        transaction.commit()
        fetchRecent()

//        val gson = Gson()
//        val i: InputStream = this.assets.open("Anime.json")
//        val br = BufferedReader(InputStreamReader(i))

//        val animeListItem: AnimeList = gson.fromJson(br, AnimeList::class.java)

//        RowListItem List is created in onder to pass the data to RowListFragment

    }

    private fun setData(){
        val rowListData = ArrayList<RowListItem>()
        for (i in 1..3) {
            rowListData.add(RowListItem("Recent Anime $i", animeListItem!!))
        }
        rowListFragment.bindData(rowListData)
//        Providing callback function here lambda for the listener
        rowListFragment.setOnContentSelectedListener { item ->
            txtTitle.text = item.title.english
            txtDescription.text = getDescription(item)
            Glide.with(this).load(item.image).into(imgBanner)
        }
    }

//    Test function

    private fun fetchRecent() {
        var call = AnimeApiInstance.animeApi.getRecent();
        call.enqueue(object : Callback<AnimeList> {
            override fun onResponse(
                call: Call<AnimeList>,
                response: Response<AnimeList>
            ) {
//                TODO("Not yet implemented")
                if (!response.isSuccessful) {
                    Log.e("Fetch Recent Int", "Code ${response.code()}")
                    return
                }
                if(response.body()!=null) {
                    animeListItem = response.body()!!
                    setData()
                }
                else
                    Log.e("Fetch Body ","Response body is null" )
            }

            override fun onFailure(call: Call<AnimeList>, t: Throwable) {
//                TODO("Not yet implemented")
                Log.e("Fetch Recent Error", t.message.toString());
            }

        })


    }

    private fun getDescription(item: Result): String {
        val builder = java.lang.StringBuilder()
        for (st in item.genres) {
            builder.append(st)
            builder.append(",")
        }
        return builder.toString()
    }
}