package com.example.bocchitv

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.example.bocchitv.Models.AnimeList
import com.example.bocchitv.Models.Result
import com.example.bocchitv.Models.RowListItem


class RowListFragment : RowsSupportFragment() {

    private var rootAdapter : ArrayObjectAdapter= ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))
    private var itemSelectedListener: ((Result)->Unit)?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setting up adapter from within the class
        adapter = rootAdapter

        //Setting up listener
        onItemViewSelectedListener = ItemViewSelectedListener()
    }

    fun bindData(rowDataList: List<RowListItem>){

        for(item in rowDataList){
            val arrayObjectAdapter= ArrayObjectAdapter(ItemPresenter())

            item.rowAnimeList.results.forEach{ it->
                arrayObjectAdapter.add(it)
            }
            val headerItem = HeaderItem(item.rowItemName)
            val listRow = ListRow(headerItem,arrayObjectAdapter)
            rootAdapter.add(listRow)

        }
    }

    fun setOnContentSelectedListener(listener: (Result)->Unit){
        this.itemSelectedListener=listener
    }

    inner class ItemViewSelectedListener : OnItemViewSelectedListener{

        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
//            TODO("Not yet implemented")
            if(item is com.example.bocchitv.Models.Result){
                itemSelectedListener?.invoke(item)
            }
        }
    }

}