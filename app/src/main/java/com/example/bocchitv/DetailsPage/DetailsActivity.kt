package com.example.bocchitv.DetailsPage

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.bocchitv.MainPage.RowListFragment
import com.example.bocchitv.Models.Details.AnimeDetails
import com.example.bocchitv.R
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class DetailsActivity : FragmentActivity() {

    lateinit var txtTitle: TextView
    lateinit var txtSubTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var imgBanner: ImageView
    lateinit var rowListFragment: DetailsRowListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        imgBanner = findViewById(R.id.img_banner)
        txtTitle = findViewById(R.id.title)
        txtDescription = findViewById(R.id.description)
        txtSubTitle = findViewById(R.id.subtitle)
        rowListFragment = DetailsRowListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, rowListFragment)
        transaction.commit()
//        fetchRecent()
        val gson = Gson()
        val inputStream = this.assets.open("AnimeDetails.json")
        val br = BufferedReader(InputStreamReader(inputStream))
        val animeDetails = gson.fromJson(br,AnimeDetails::class.java)

        rowListFragment.bindData(animeDetails)

        rowListFragment.setOnContentSelectedListener { item ->

        }

    }


}