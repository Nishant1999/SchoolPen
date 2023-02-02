package com.asmanmirza.schoolpen.UI.Teacher.Home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asmanmirza.schoolpen.databinding.ActivityTeacherLiveMeetingBinding

class TeacherLiveMeetingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherLiveMeetingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTeacherLiveMeetingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartLiveClass.setOnClickListener {
            var intent = Intent(this, TeacherStartLiveClassActivity::class.java)
            startActivity(intent)
        }

        binding.btnScheduleClass.setOnClickListener {
            var intent = Intent(this, TeacherSceduleClassActivity::class.java)
            startActivity(intent)
        }


        binding.btnScheduleMeeting.setOnClickListener {
            var intent = Intent(this, TeacherScheduleMeetingActivity::class.java)
            startActivity(intent)
        }

        binding.btnJoinMeeting.setOnClickListener {
            var intent = Intent(this, JoinMeetingActivity::class.java)
            startActivity(intent)
        }


    }
}
