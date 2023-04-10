package com.example.bocchitv

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.example.bocchitv.Models.SearchAnimeResults

class SearchItemPresenter:Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.details_card_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val content= item as? SearchAnimeResults.Result

        val imageView = viewHolder!!.view.findViewById<ImageView>(R.id.poster_image)
        val typeTv= viewHolder!!.view.findViewById<TextView>(R.id.relation_tv)
        val titleTv = viewHolder!!.view.findViewById<TextView>(R.id.title_tv)
        val url = content?.image
        Glide.with(viewHolder?.view?.context!!)
            .load(url)
            .into(imageView!!)

        typeTv.text= content?.type.toString()
        titleTv.text= content?.title!!.english
        if(titleTv.text.toString().trim().isEmpty()){
            titleTv.text= content.title.romaji
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
//        TODO("Not yet implemented")

    }
}