package com.example.bocchitv.DetailsPage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.example.bocchitv.Models.Details.Relation
import com.example.bocchitv.R

class RelatedPresenter: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
//        TODO("Not yet implemented")
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.details_card_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
//        TODO("Not yet implemented")
        val content = item as? Relation
        val imageView = viewHolder!!.view.findViewById<ImageView>(R.id.poster_image)
        val relationTv= viewHolder!!.view.findViewById<TextView>(R.id.relation_tv)
        val titleTv = viewHolder!!.view.findViewById<TextView>(R.id.title_tv)

        val url = content?.image
        Glide.with(viewHolder?.view?.context!!)
            .load(url)
            .into(imageView!!)

        relationTv.text= content?.relationType
        titleTv.text= content?.title!!.english
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
//        TODO("Not yet implemented")
    }
}