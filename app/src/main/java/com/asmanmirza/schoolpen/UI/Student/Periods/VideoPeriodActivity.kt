package com.asmanmirza.schoolpen.UI.Student.Periods

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityVideoPeriodBinding
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

class VideoPeriodActivity : AppCompatActivity() {

    lateinit var binding: ActivityVideoPeriodBinding
    var exoPlayerView: SimpleExoPlayerView? = null
    var exoPlayer: SimpleExoPlayer? = null
    var videoURL =
        "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoPeriodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeData()
    }

    fun initializeData(){

        binding.apply {

            exoPlayerView = binding?.video
            try{

                // BandwidthMeter is used for
                // getting default bandwidth
                val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()

                // track selector is used to navigate between
                // video using a default seekbar.
                val trackSelector: TrackSelector =
                    DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))

                // we are adding our track selector to exoplayer.
                exoPlayer = ExoPlayerFactory.newSimpleInstance(this@VideoPeriodActivity, trackSelector)

                // we are parsing a video url
                // and parsing its video uri.
                val videouri: Uri = Uri.parse(videoURL)

                // we are creating a variable for datasource factory
                // and setting its user agent as 'exoplayer_view'
                val dataSourceFactory = DefaultHttpDataSourceFactory("exoplayer_video")

                // we are creating a variable for extractor factory
                // and setting it to default extractor factory.
                val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()

                // we are creating a media source with above variables
                // and passing our event handler as null,
                val mediaSource: MediaSource =
                    ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null)

                // inside our exoplayer view
                // we are setting our player
                exoPlayerView!!.player = exoPlayer

                // we are preparing our exoplayer
                // with media source.
                exoPlayer!!.prepare(mediaSource)

                // we are setting our exoplayer
                // when it is ready.
                exoPlayer!!.playWhenReady = true

            }catch (e:Exception) {
                // below line is used for
                // handling our errors.
                Log.e("TAG", "Error : " + e.toString());
            }
        }
    }

    companion object{

        fun startActivity(context: Context){

            val intent = Intent(context, VideoPeriodActivity::class.java)
            context.startActivity(intent)
        }
    }
}