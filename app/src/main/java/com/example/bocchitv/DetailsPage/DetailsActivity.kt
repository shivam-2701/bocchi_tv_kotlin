package com.example.bocchitv.DetailsPage

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.bocchitv.MainPage.RowListFragment
import com.example.bocchitv.Models.Details.AnimeDetails
import com.example.bocchitv.Models.Main.AnimeList
import com.example.bocchitv.Networking.AnimeApiInstance
import com.example.bocchitv.R
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class DetailsActivity : FragmentActivity() {

    lateinit var txtTitle: TextView
    lateinit var txtSubTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var imgBanner: ImageView
    lateinit var rowListFragment: DetailsRowListFragment
    lateinit var animeDetails :AnimeDetails
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        imgBanner = findViewById(R.id.img_banner)
        txtTitle = findViewById(R.id.title)
        txtDescription = findViewById(R.id.description)
        txtSubTitle = findViewById(R.id.subtitle)

        val extra = intent.extras;
        val episodeId: String? = extra!!.getString("EpisodeId")
        if (episodeId != null) {
            fetchEpisodeData(episodeId)
        }
        else{
            fetchEpisodeData()
        }
        rowListFragment = DetailsRowListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, rowListFragment)
        transaction.commit()

    }
    private  fun setData(){
        rowListFragment.bindData(animeDetails)

        rowListFragment.setOnContentSelectedListener { item->
            txtTitle.text= item.number.toString()
            txtDescription.text = "Test Description"
            Glide.with(this).load(item.image).into(imgBanner)
        }
    }

    private fun fetchEpisodeData(episodeId:String="14813"){
       var call = AnimeApiInstance.animeApi.getEpisodeById(episodeId)

       call.enqueue(object : Callback<AnimeDetails> {
           override fun onResponse(
               call: Call<AnimeDetails>, response: Response<AnimeDetails>
           ) {
               if (!response.isSuccessful) {
                   Log.e("Fetch Episode Int", "Code ${response.code()}")
               }
               if (response.body() != null) {
                   animeDetails = response.body()!!
                  return setData()
               } else Log.e("Fetch Body ", "Response body is null")

           }

           override fun onFailure(call: Call<AnimeDetails>, t: Throwable) {
//                TODO("Not yet implemented")
               Log.e("Fetch Episode Error", t.message.toString());
           }

       })

    }

}

