package com.asmanmirza.schoolpen.UI.Student.Courses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Courses.Adapters.TabsAdapter
import com.asmanmirza.schoolpen.databinding.ActivityCourseDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class CourseDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityCourseDetailBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    fun updateData(){

        binding.apply {

           btnBack.setOnClickListener{
               finish()
            }
            viewPager.adapter = TabsAdapter(supportFragmentManager,lifecycle)
            TabLayoutMediator(tabLayout,viewPager){tab,position->
                tab.text = when(position){
                    0 -> "About Course"
                    1 -> "Syllabus"
                    2 -> "Instructor"
                    3 -> "Reviews"
                    4 -> "Certificate"
                    else -> ""
                }
            }.attach()

        }

    }

}