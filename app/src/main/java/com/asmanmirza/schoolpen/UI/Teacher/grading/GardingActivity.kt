package com.asmanmirza.schoolpen.UI.Teacher.grading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.R

class GardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.grading_subject_work_fragment_container,
            GradingFragment()
        ).commit()
        setContentView(R.layout.activity_garding)
    }
}