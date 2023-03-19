package com.example.bocchitv

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.FragmentActivity
import com.example.bocchitv.Models.Details.AnimeDetails
import com.example.bocchitv.Models.Details.Episode
import com.example.bocchitv.utils.getVideoSource
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.MimeTypes
import java.io.Serializable

class VideoPlayerActivity:FragmentActivity(), Player.Listener {
    private lateinit var simpleExoPlayer: ExoPlayer
    private var playbackPosition: Long =0
    private var mediaUrl = "https://eu-11.files.nextcdn.org/hls/11/07/4356426c58a8b20f5b0caa306d5b08315b81e0ded059782f6958d43c87efef9b/owo.m3u8"
    private lateinit var playerView: StyledPlayerView
    private lateinit var progressBar: ProgressBar
    private var animeDetails: AnimeDetails? =null
    private  var episodeNo=0
    private val dataSourceFactory= DefaultHttpDataSource.Factory();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player);
        playerView= findViewById(R.id.exoplayerView)
        progressBar= findViewById(R.id.progressBar)

        if(intent.extras!=null){
            animeDetails= intent.getParcelableExtra("animeDetails") as AnimeDetails?
            episodeNo= intent.getStringExtra("episodeNo")?.toInt() ?: 1
        }
        setVideoSource(animeDetails!!,episodeNo)
    }
    override fun onStart(){
        super.onStart()
        setVideoSource(animeDetails!!,episodeNo)
    }
    override fun onStop() {
        super.onStop()
        releasePlayer()
    }
    private fun initializePlayer(){
        simpleExoPlayer = ExoPlayer.Builder(this).build()
        preparePlayer(mediaUrl)
        playerView.player=simpleExoPlayer
        simpleExoPlayer.seekTo(playbackPosition)
        simpleExoPlayer.playWhenReady = true
        simpleExoPlayer.addListener(this)
    }

    private fun preparePlayer(videoUrl:String){
        val uri = Uri.parse(videoUrl)
        val mediaItem = MediaItem.Builder()
            .setUri(mediaUrl)
            .setMimeType(MimeTypes.APPLICATION_M3U8) //m3u8 is the extension used with HLS sources
            .build()
        simpleExoPlayer.setMediaItem(mediaItem)

        simpleExoPlayer.prepare()
    }


    private fun buildMediaSource(uri: Uri): HlsMediaSource {

        val mediaItem = MediaItem.Builder()
            .setUri(mediaUrl)
            .setMimeType(MimeTypes.APPLICATION_M3U8) //m3u8 is the extension used with HLS sources
            .build()
        val hlsMediaSource= HlsMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaItem);
        return hlsMediaSource
    }

    private fun releasePlayer() {
        playbackPosition = simpleExoPlayer.currentPosition
        simpleExoPlayer.release()
    }
    override fun onPlayerError(error: PlaybackException) {
        Log.e("Player Error", Log.getStackTraceString(error))
    }

    private fun setVideoSource(animeDetails: AnimeDetails,episodeNo:Int) {

        getVideoSource(animeDetails,episodeNo){videoUrl->
            if(!videoUrl.isEmpty()){
                for(el in videoUrl){
                    if(el["resolution"]=="1080"){
                        mediaUrl=el["url"]!!
                        mediaUrl= mediaUrl.replace("cache","files")
                        Log.d("MediaUrl",mediaUrl)
                        break
                    }
                }
            }
            runOnUiThread {
                progressBar.visibility= View.INVISIBLE
                initializePlayer()
            }
        }


    }
//    fun <T : Serializable?> Intent.getParcelableExtra(key: String, m_class: Class<T>): T {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
//            this.getParcelableExtra(key, m_class)!!
//        else
//            this.getParcelableExtra(key) as T
//    }
}