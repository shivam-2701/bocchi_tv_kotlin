package com.example.bocchitv.Fragments

import android.app.Activity
import android.content.Context
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
import androidx.leanback.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.bocchitv.R
import com.example.bocchitv.SearchItemPresenter
import com.example.bocchitv.SearchRepository


class SearchFragment :Fragment(){

    lateinit var searchEditText: SearchEditText
    lateinit var animeVGridView:VerticalGridView
    lateinit var searchRepository:SearchRepository
    val adapter = ItemBridgeAdapter()
    var mAdapter=ArrayObjectAdapter()

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
            val Adapter= ArrayObjectAdapter(SearchItemPresenter())
            it.results!!.forEach {
                Adapter.add(it)
            }
            mAdapter=Adapter
            adapter.setAdapter(Adapter)
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
       animeVGridView.setOnChildSelectedListener(object :OnChildSelectedListener{
           override fun onChildSelected(parent: ViewGroup?, view: View?, position: Int, id: Long) {
//               TODO("Not yet implemented")
               Log.d("SELECTED ITEM",mAdapter[position].toString())
           }

       })





    }
    fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}