package com.example.bocchitv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.example.bocchitv.Models.Main.Result

class ItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
//        TODO("Not yet implemented")
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false);
        val params = view.layoutParams
        params.width = getItemWidth(parent!!.context, 12)
        params.height = getItemHeight(parent!!.context, 32)
        return ViewHolder(view)
    }

    private fun getItemWidth(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    private fun getItemHeight(context: Context, percent: Int): Int {
        val height = context.resources.displayMetrics.heightPixels ?: 0
        return (height * percent) / 100
    }


    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
//        TODO("Not yet implemented")
        val content = item as? Result

        val imageView = viewHolder?.view?.findViewById<ImageView>(R.id.poster_image)

        val url = content?.image
        Glide.with(viewHolder?.view?.context!!)
            .load(url)
            .into(imageView!!)

    }
    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
//        TODO("Not yet implemented")
    }
}