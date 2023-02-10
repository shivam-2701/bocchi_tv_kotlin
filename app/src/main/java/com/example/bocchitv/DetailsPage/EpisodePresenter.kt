package com.example.bocchitv.DetailsPage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.example.bocchitv.Models.Details.Episode
import com.example.bocchitv.R

class EpisodePresenter:Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
//        TODO("Not yet implemented")
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.episode_item, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
//        TODO("Not yet implemented")
        val content = item as? Episode
        val imageView = viewHolder!!.view.findViewById<ImageView>(R.id.poster_image)
        val episodeTv = viewHolder!!.view.findViewById<TextView>(R.id.episodeNo_tv)

        val url = content?.image
        Glide.with(viewHolder?.view?.context!!)
            .load(url)
            .into(imageView!!)

        episodeTv.text= content?.number.toString()
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
//        TODO("Not yet implemented")
    }
}