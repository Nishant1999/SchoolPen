package com.asmanmirza.schoolpen.UI.Teacher.Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.databinding.ActivityProfileTeacherBinding

class ProfileTeacherActivity : AppCompatActivity() {

    lateinit var binding:ActivityProfileTeacherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {

            backButton.setOnClickListener {
                finish()
            }

        }

    }
}