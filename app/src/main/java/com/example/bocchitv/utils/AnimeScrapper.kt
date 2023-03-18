package com.example.bocchitv.utils

import android.util.Log
import com.example.bocchitv.Models.Animepahe.AnimePaheEpisosdeResult
import com.example.bocchitv.Models.Animepahe.AnimepaheSearch
import com.example.bocchitv.Networking.AnimePaheApiInstance

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.IOException
import java.net.URLEncoder

val apiUrl = "https://relieved-cyan-tuxedo.cyclic.app"

fun extractSourceList(videoElement: Element): List<HashMap<String, String>> {
    val children = videoElement.children();
    val arrayList: ArrayList<HashMap<String, String>> = ArrayList()
    for (element in children) {
        val attr = element.attributes()
        val hMap: HashMap<String, String> = HashMap()
        hMap["referrer"] = attr["data-src"].toString()
        hMap["resolution"] = attr["data-resolution"].toString()
        hMap["audio"] = attr["data-audio"].toString()
        hMap["group"] = attr["data-fansub"].toString()
        arrayList.add(hMap)
    }
    return arrayList
}

fun getKwikUrl(jsonArray: JSONArray): String {
    val httpClient: OkHttpClient = OkHttpClient()
    val mediaType = "application/json; charset=utf-8".toMediaType()
    val requestBody = jsonArray.toString().toRequestBody(mediaType)
    val url = apiUrl + "/watch?url=" + URLEncoder.encode(jsonArray.toString(), "UTF-8");
    val request = Request.Builder().url(url).build()
    val response = httpClient.newCall(request).execute()
    val responseString = response.body!!.string()
    return responseString
}

fun getVideoSource(updateUi: (List<HashMap<String, String>>) -> Unit) {
    val animeId = "00e25f1b-3af1-92ae-46f5-3f46a5b944c2"
    val episodeId = "8e805884b225721fdb1a7bd7eb220daaf8e8ba3b1fb7af72dd06f770a0094950"
    val url = "https://animepahe.ru/play/$animeId/$episodeId"
    Thread(Runnable {

        try {
            val conn = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Linux; Android 13) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.5563.57 Mobile Safari/537.36")
                .timeout(10000)
            val doc = conn.get()
            val sourceList = extractSourceList(doc.getElementById("resolutionMenu")!!)
            val streamInfoList = doc.getElementById("pickDownload")?.children()
            val referrerList = ArrayList<HashMap<String, String>>()
            for (e in sourceList) {
                val mp = HashMap<String, String>()
                mp.put("url", e["referrer"]!!)
                mp.put("audio", e["audio"]!!)
                mp.put("resolution", e["resolution"]!!)
                referrerList.add(mp)
            }
            val jsonObj = ArrayList<JSONObject>()

            for (data in referrerList) {
                jsonObj.add(JSONObject(data as MutableMap<Any?, Any?>))
            }
            val jsonArray = JSONArray(jsonObj)
            val responseString = getKwikUrl(jsonArray)

            val kwikArray = JSONArray(responseString)

            val size = kwikArray.length()
            for (el in sourceList) {
                for (i in 0 until size) {
                    if (kwikArray.getJSONObject(i).getString("audio").equals(el["audio"]) &&
                        kwikArray.getJSONObject(i).getString("resolution").equals(el["resolution"])
                    ) {
                        el.put("url", kwikArray.getJSONObject(i).getString("url"))
                        break
                    }
                }
            }
            for (el in sourceList) {
                Log.d("SOURCE List", el.toString())
            }
            updateUi.invoke(sourceList)
        } catch (error: IOException) {
            Log.e("GET VideoSource Error", Log.getStackTraceString(error))
        }

    }).start()

}

fun getAnimepaheId(query: String, releasedYear: String, season: String = "unknown"): String {

    try {
        val call = AnimePaheApiInstance.animePaheApi.fetchAnimePaheSearchList(query!!)
        val response = call.execute()
        if (response.isSuccessful) {
            val result: AnimepaheSearch = response.body()!!
            for(it in result.data!!){
                if(season=="unknown" && it!!.year.toString()==releasedYear){
                    return it.session!!
                }
                else if(it!!.year.toString()== releasedYear && it.season.toString()==season){
                    return it.session!!
                }
            }

        } else {
            Log.e("FETCH ANIME ID ERROR", response.errorBody().toString())
        }
    } catch (error: java.lang.Exception) {
        Log.e("FETCH ANIME ID ERROR", Log.getStackTraceString(error))
    }
    return ""
}
fun fetchAnimePaheEpisodes(animeId:String, page:Int):AnimePaheEpisosdeResult{

    if(animeId==""){
        Log.e("FETCH ANIME EPISODES","No anime found")
        return AnimePaheEpisosdeResult()
    }else {

        try {

            val call =
                AnimePaheApiInstance.animePaheApi.fetchAnimepaheEpisodes(animeId, page.toString())

            val response = call.execute()

            if (response.isSuccessful) {
                return response.body()!!


            } else {
                Log.e("FETCH ANIME EPISODES", response.errorBody().toString())
            }

        } catch (error: Exception) {
            Log.e("FETCH ANIME EPISODES", Log.getStackTraceString(error))
        }
        return AnimePaheEpisosdeResult()
    }

}
