package com.asmanmirza.schoolpen.UI.Student.Periods

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asmanmirza.schoolpen.R

class AudioPeriodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_period)
    }

    companion object{

        fun startActivity(context: Context){

            val intent = Intent(context, AudioPeriodActivity::class.java)
            context.startActivity(intent)
        }
    }
}