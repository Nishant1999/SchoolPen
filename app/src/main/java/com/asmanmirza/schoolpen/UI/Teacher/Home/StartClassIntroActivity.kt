package com.asmanmirza.schoolpen.UI.Teacher.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.databinding.ActivityStartClassIntroBinding

class StartClassIntroActivity : AppCompatActivity() {

    lateinit var binding: ActivityStartClassIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartClassIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData() {
        binding.apply {
            backButton.setOnClickListener {
                finish()
            }
            btnAttendance.setOnClickListener {
                startActivity(Intent(this@StartClassIntroActivity, AttendanceActivity::class.java))
            }
            skipButton.setOnClickListener {

            }
        }
    }
}