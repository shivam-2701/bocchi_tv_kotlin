package com.example.bocchitv.Fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.leanback.widget.*
import com.example.bocchitv.R
import com.example.bocchitv.SearchItemPresenter
import com.example.bocchitv.SearchRepository


class SearchFragment :Fragment() {

    lateinit var searchEditText: SearchEditText
    lateinit var animeVGridView:VerticalGridView
    lateinit var searchRepository:SearchRepository
    val adapter = ItemBridgeAdapter()

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
        animeVGridView= view.findViewById(R.id.search_grid_view)
        searchRepository=SearchRepository()
        animeVGridView.adapter=adapter
        searchRepository.getSearchResult().observe(viewLifecycleOwner){
            val mAdapter= ArrayObjectAdapter(SearchItemPresenter())

            it.results!!.forEach {
                mAdapter.add(it)
            }
            adapter.setAdapter(mAdapter)
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

    }
    fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}