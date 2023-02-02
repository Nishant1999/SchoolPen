package com.asmanmirza.schoolpen.UI.Teacher.grading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.R

class GradingSubjectWorkActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.grading_subject_work_fragment_container,
            GradingSubjectWorkFragment()
        ).commit()
        setContentView(R.layout.activity_grading_subject_work)
    }

}