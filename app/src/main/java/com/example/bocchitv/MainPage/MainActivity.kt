package com.example.bocchitv.MainPage

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.leanback.widget.BrowseFrameLayout
import com.example.bocchitv.R
import com.example.bocchitv.utils.Common

class MainActivity : FragmentActivity(), View.OnKeyListener {


    lateinit var btnSearch:TextView
    lateinit var btnHome:TextView
    lateinit var btnWatchlist:TextView
    lateinit var btnSettings:TextView
    lateinit var btnUserPreferences:TextView
    lateinit var navBar:BrowseFrameLayout
    var SIDE_MENU= false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSearch= findViewById(R.id.btn_search)
        btnHome= findViewById(R.id.btn_home)
        btnWatchlist= findViewById(R.id.btn_watchlist)
        btnSettings= findViewById(R.id.btn_settings)
        btnUserPreferences= findViewById(R.id.btn_preferences)
        navBar = findViewById(R.id.blfNavBar)

        btnSearch.setOnKeyListener(this)
        btnHome.setOnKeyListener(this)
        btnWatchlist.setOnKeyListener(this)
        btnSettings.setOnKeyListener(this)
        btnUserPreferences.setOnKeyListener(this)


        changeFragment(HomeFragment())
    }
    fun changeFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment).addToBackStack(null).commit()
    }

    override fun onKey(view: View?, i: Int,keyEvent: KeyEvent?): Boolean {
//        TODO("Not yet implemented")
        when(i){
            KeyEvent.KEYCODE_DPAD_LEFT ->{
                if(!SIDE_MENU){
                    openMenu()
                    SIDE_MENU=true
                }
            }
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT && SIDE_MENU) {
            SIDE_MENU= false
            closeMenu()
        }
        return super.onKeyDown(keyCode, event)
    }

    fun openMenu(){
        navBar.requestLayout()
        navBar.layoutParams.width=Common.getWidthInPercent(this,16)

    }
    fun closeMenu(){
        navBar.requestLayout()
        navBar.layoutParams.width=Common.getWidthInPercent(this,5)
    }

}