package com.example.bocchitv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.example.bocchitv.Models.RowListItem


class RowListFragment : RowsSupportFragment() {

    private var rootAdapter : ArrayObjectAdapter= ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter =  rootAdapter
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


}