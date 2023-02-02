package com.asmanmirza.schoolpen.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.asmanmirza.schoolpen.UI.Student.Fragments.ClassworkFragment
import com.asmanmirza.schoolpen.UI.Student.Fragments.CoursesFragment
import com.asmanmirza.schoolpen.UI.Student.Fragments.HomeFragment
import com.asmanmirza.schoolpen.UI.Student.Fragments.LearningFragment
import com.asmanmirza.schoolpen.UI.Student.Performance.StudentPerformanceFragment

class PageAdapterStudents(var fm: FragmentManager, var tabs:Int): FragmentPagerAdapter(fm, tabs) {

    override fun getCount(): Int {
        return tabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0->{
                HomeFragment()
            }
            1->{
                LearningFragment()
            }
            2->{
                ClassworkFragment()
            }
            3->{
                CoursesFragment()
            }
            4->{
                StudentPerformanceFragment()
            }
            else -> {
                HomeFragment()
            }
        }
    }
}