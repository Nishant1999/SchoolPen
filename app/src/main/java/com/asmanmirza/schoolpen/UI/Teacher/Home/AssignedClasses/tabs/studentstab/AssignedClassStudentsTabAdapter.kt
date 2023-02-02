package com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.studentstab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class AssignedClassStudentsTabAdapter(var fm: FragmentManager, var tabs:Int): FragmentPagerAdapter(fm, tabs) {

    override fun getCount(): Int {
        return tabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0->{
                StudentDetailsFragment()
            }
            1->{
                StudentAttendanceFragment()
            }
            2->{
                StudentsGradeFragment()
            }
            else -> {
                StudentDetailsFragment()
            }
        }
    }
}