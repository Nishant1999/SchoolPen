package com.asmanmirza.schoolpen.UI.Student.Classwork.grades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.databinding.ActivitySubjectTperformanceStatusBinding

class SubjectTPerformanceStatusActivity : AppCompatActivity() {

    lateinit var binding:ActivitySubjectTperformanceStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectTperformanceStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    fun updateData(){

        binding.apply {

            btnBack.setOnClickListener {
                finish()
            }

        }

    }

}