package com.example.bocchitv.DetailsPage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.example.bocchitv.MainPage.ItemPresenter
import com.example.bocchitv.Models.Details.AnimeDetails
import com.example.bocchitv.Models.Details.Episode
import com.example.bocchitv.Models.Details.Relation
import com.example.bocchitv.Models.Main.Result
import com.example.bocchitv.Models.Main.RowListItem

class DetailsRowListFragment: RowsSupportFragment() {

    private var rootAdapter : ArrayObjectAdapter = ArrayObjectAdapter(
        ListRowPresenter(
            FocusHighlight.ZOOM_FACTOR_MEDIUM,true)
    )
    private var episodeItemSelectedListener: ((Episode)->Unit)?= null
    private var relatedItemSelectedListener: ((Relation)->Unit)?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setting up adapter from within the class
        adapter = rootAdapter

        //Setting up listener
        onItemViewSelectedListener = ItemViewSelectedListener()
    }

    fun bindData(animeData : AnimeDetails ){


        val episodeObjectAdapter = ArrayObjectAdapter(EpisodePresenter())

        animeData.episodes?.forEach { it->
            episodeObjectAdapter.add(it)
        }
        val episodeHeaderItem= HeaderItem("Episodes")
        val listRow = ListRow(episodeHeaderItem,episodeObjectAdapter)
        rootAdapter.add(listRow)
        if(animeData.relations!=null){
        setRelatedRow(animeData.relations)
        }
    }

    fun setOnContentSelectedListener(listener: (Episode)->Unit){
        this.episodeItemSelectedListener=listener
    }


    private fun setRelatedRow(itemList: List<Relation>){

        val relatedObjectAdapter = ArrayObjectAdapter(RelatedPresenter())
        itemList.forEach { it->
            relatedObjectAdapter.add(it)
        }
        val headerItem = HeaderItem("Related Media")
        val listRow = ListRow(headerItem,relatedObjectAdapter)
        rootAdapter.add(listRow)
    }




    inner class ItemViewSelectedListener : OnItemViewSelectedListener {

        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
//            TODO("Not yet implemented")
           if( row !=null && row!!.headerItem!!.name=="Episodes" && item is Episode){
                episodeItemSelectedListener?.invoke(item as Episode)
           }else{
               Log.d("Selection Listener","Listener Activated")
           }
        }
    }

}