package com.example.bocchitv

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.bocchitv.Models.Main.AnimeList
import com.example.bocchitv.Models.Main.RowListItem
import com.example.bocchitv.Models.Main.Result
import com.example.bocchitv.Networking.AnimeApiInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : FragmentActivity() {

    val HEADER_RECENT="Recent Anime"
    val HEADER_TRENDING="Trending Anime"
    val HEADER_POPULAR="Popular"
    lateinit var txtTitle: TextView
    lateinit var txtSubTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var imgBanner: ImageView
    lateinit var rowListFragment: RowListFragment
//    val rowListData = ArrayList<RowListItem>()
    var isListenerSet = false
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

    }

    private fun setData(headerName: String,animeListItem: AnimeList) {
        Log.d("Set DATA",headerName)
        rowListFragment.bindData(RowListItem(headerName, animeListItem!!))
        if (!isListenerSet) {
            isListenerSet = true;
            rowListFragment.setOnContentSelectedListener { item ->
                txtTitle.text = item.title.english
                txtDescription.text = getDescription(item)
                Glide.with(this).load(item.image).into(imgBanner)
            }
        }
    }

    private fun fetchRecent() {
        var call = AnimeApiInstance.animeApi.getRecent();
        var animeListItem: AnimeList? = null
        call.enqueue(object : Callback<AnimeList> {
            override fun onResponse(
                call: Call<AnimeList>, response: Response<AnimeList>
            ) {
                if (!response.isSuccessful) {
                    Log.e("Fetch Recent Int", "Code ${response.code()}")
                    return fetchTrending()
                }
                if (response.body() != null) {
                    animeListItem = response.body()!!
                    setData(HEADER_RECENT, animeListItem!!)
                } else Log.e("Fetch Body ", "Response body is null")
                fetchTrending()
            }

            override fun onFailure(call: Call<AnimeList>, t: Throwable) {
//                TODO("Not yet implemented")
                Log.e("Fetch Recent Error", t.message.toString());
                fetchTrending()
            }

        })
    }

    private fun fetchTrending() {
        var call = AnimeApiInstance.animeApi.getTrending();
        var animeListItem: AnimeList? = null
        call.enqueue(object : Callback<AnimeList> {
            override fun onResponse(
                call: Call<AnimeList>, response: Response<AnimeList>
            ) {
                if (!response.isSuccessful) {
                    Log.e("Fetch Trending Int", "Code ${response.code()}")
                    return fetchPopular()
                }
                if (response.body() != null) {
                    animeListItem = response.body()!!
                    setData(HEADER_TRENDING, animeListItem!!)
                } else Log.e("Fetch Body ", "Response body is null")
                fetchPopular()
            }

            override fun onFailure(call: Call<AnimeList>, t: Throwable) {
//                TODO("Not yet implemented")
                Log.e("Fetch Trending Error", t.message.toString());
                fetchPopular()
            }
        })
    }
    private fun fetchPopular() {
        var call = AnimeApiInstance.animeApi.getPopular();
        var animeListItem: AnimeList? = null
        call.enqueue(object : Callback<AnimeList> {
            override fun onResponse(
                call: Call<AnimeList>, response: Response<AnimeList>
            ) {
                if (!response.isSuccessful) {
                    Log.e("Fetch Popular Int", "Code ${response.code()}")
                    return
                }
                if (response.body() != null) {
                    animeListItem = response.body()!!
                    setData(HEADER_POPULAR, animeListItem!!)
                } else Log.e("Fetch Body ", "Response body is null")
            }

            override fun onFailure(call: Call<AnimeList>, t: Throwable) {
//                TODO("Not yet implemented")
                Log.e("Fetch Trending Error", t.message.toString());
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