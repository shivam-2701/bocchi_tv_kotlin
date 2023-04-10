package com.example.bocchitv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bocchitv.Models.SearchAnimeResults
import com.example.bocchitv.Networking.AnimeApiInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchRepository {

    var query:String=""
    private val searchLiveData = MutableLiveData<SearchAnimeResults>()
    fun getSearchResult():LiveData<SearchAnimeResults>{
        makeRequest()
        return searchLiveData
    }
    fun makeRequest(){
        if(!query.isEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val result= AnimeApiInstance.animeApi.getSearchResult(query)
                    searchLiveData.postValue(result)
                }catch (e:java.lang.Exception){
                    Log.e("Search Exception", e.cause.toString())
                }
            }
        }
    }

}