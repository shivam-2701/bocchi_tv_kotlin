package com.example.bocchitv.MainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.bocchitv.R

class MainActivity : FragmentActivity() {


    lateinit var btnSearch:TextView
    lateinit var btnHome:TextView
    lateinit var btnWatchlist:TextView
    lateinit var btnSettings:TextView
    lateinit var btnUserPreferences:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSearch= findViewById(R.id.btn_search)
        btnHome= findViewById(R.id.btn_home)
        btnWatchlist= findViewById(R.id.btn_watchlist)
        btnSettings= findViewById(R.id.btn_settings)
        btnUserPreferences= findViewById(R.id.btn_preferences)
        changeFragment(HomeFragment())
    }
    fun changeFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment).addToBackStack(null).commit()
    }
}