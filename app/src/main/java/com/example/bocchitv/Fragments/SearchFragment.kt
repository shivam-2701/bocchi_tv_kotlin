package com.example.bocchitv.Fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.bocchitv.MainPage.RowListFragment
import com.example.bocchitv.R
import com.example.bocchitv.SearchItemPresenter
import com.example.bocchitv.SearchRepository
import com.example.bocchitv.SearchVerticalGridFragment


class SearchFragment :Fragment(){

    lateinit var searchEditText: SearchEditText
    lateinit var searchRepository:SearchRepository
    lateinit var animeSearchGV: SearchVerticalGridFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {
        searchEditText= view.findViewById(R.id.search_edit_text)
        animeSearchGV = SearchVerticalGridFragment()
        val transaction =  childFragmentManager.beginTransaction()
        transaction.add(R.id.search_grid_view, animeSearchGV)
        transaction.commit()
        searchRepository=SearchRepository()
        searchRepository.getSearchResult().observe(viewLifecycleOwner){
            animeSearchGV.updateCards(it.results!!)
        }
        searchEditText.apply {
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        searchRepository.query = searchEditText.text.toString().trim()
                        searchRepository.makeRequest()
                        hideKeyboardFrom(context,view)
                        true
                    }
                    else->false
                }
            }
        }

        animeSearchGV.onItemViewClickedListener= object:OnItemViewClickedListener{
            override fun onItemClicked(
                itemViewHolder: Presenter.ViewHolder?,
                item: Any?,
                rowViewHolder: RowPresenter.ViewHolder?,
                row: Row?
            ) {
                val intent= Intent()

            }

        }
    }
    fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}