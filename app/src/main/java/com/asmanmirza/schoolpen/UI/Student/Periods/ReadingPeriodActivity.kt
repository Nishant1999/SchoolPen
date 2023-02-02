package com.asmanmirza.schoolpen.UI.Student.Periods

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityReadingPeriodBinding

class ReadingPeriodActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityReadingPeriodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReadingPeriodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            imgAudio.setOnClickListener(this@ReadingPeriodActivity)
            imgSetting.setOnClickListener(this@ReadingPeriodActivity)
        }
    }

    companion object{

        fun startActivity(context: Context){

            val intent = Intent(context, ReadingPeriodActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

        when(p0){

            binding.imgAudio -> {
                AudioPeriodActivity.startActivity(this@ReadingPeriodActivity)
            }

            binding.imgSetting -> {
                VideoPeriodActivity.startActivity(this@ReadingPeriodActivity)
            }
        }
    }
}