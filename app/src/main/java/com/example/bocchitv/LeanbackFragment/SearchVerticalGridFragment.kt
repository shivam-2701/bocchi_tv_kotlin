package com.example.bocchitv.LeanbackFragment

import android.os.Bundle
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.VerticalGridPresenter
import com.example.bocchitv.Models.Utility.SearchAnimeResults
import com.example.bocchitv.Presenter.SearchItemPresenter

class SearchVerticalGridFragment : VerticalGridSupportFragment() {

    private lateinit var mAdapter: ArrayObjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRowAdapter()
    }

    private fun setupRowAdapter(){
        val gridPresenter = VerticalGridPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM)
        gridPresenter.numberOfColumns= 5
        setGridPresenter(gridPresenter)
        mAdapter= ArrayObjectAdapter(SearchItemPresenter())
        adapter= mAdapter
    }

     fun updateCards(results:List<SearchAnimeResults.Result?>){
        mAdapter.clear()
        results.forEach {
            mAdapter.add(it)
        }
    }

}