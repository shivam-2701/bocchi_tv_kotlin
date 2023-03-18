package com.example.bocchitv

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.bocchitv.utils.getVideoSource


class NetworkActivity :FragmentActivity() {

    private lateinit var textView:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)
        textView= findViewById<TextView>(R.id.json_textView)
//        getVideoSource { response:List<HashMap<String,String>>->
//            runOnUiThread{
//                val builder= StringBuilder()
//                for(r in response){
//                    builder.append(r.toString() +"\n")
//                }
//                textView.text= builder.toString()
//            }
//        }
    }
}