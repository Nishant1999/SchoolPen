package com.asmanmirza.schoolpen.UI.Student.Home

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.navigation.fragment.findNavController
import com.asmanmirza.schoolpen.databinding.ActivityLiveClassDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class LiveClassDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityLiveClassDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveClassDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    fun updateData(){

        binding.apply {

            videoView.visibility = View.INVISIBLE
            val url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
            val mediaController = MediaController(this@LiveClassDetailActivity)
            mediaController.setAnchorView(videoView)
            mediaController.setMediaPlayer(videoView)
            videoView.setMediaController(mediaController)
            videoView.setVideoURI(Uri.parse(url))
            Glide.with(this@LiveClassDetailActivity).load(url).thumbnail(0.8f).diskCacheStrategy(
                DiskCacheStrategy.ALL).into(thumbnailVideo)
            Glide.with(this@LiveClassDetailActivity).load("https://api.lorem.space/image/face?w=150&h=150").thumbnail(0.8f).diskCacheStrategy(
                DiskCacheStrategy.ALL).into(imageDp)

            btnPlay.setOnClickListener {
                thumbnailVideo.visibility = View.GONE
                btnPlay.visibility = View.GONE
                videoView.start()
                videoView.visibility = View.VISIBLE
            }

            btnBack.setOnClickListener {
                finish()
            }

            videoView.setOnCompletionListener {
                thumbnailVideo.visibility = View.VISIBLE
                btnPlay.visibility = View.VISIBLE
                videoView.visibility = View.INVISIBLE
            }

        }

    }

}