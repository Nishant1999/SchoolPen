package com.asmanmirza.schoolpen.UI.Teacher.Home.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asmanmirza.schoolpen.UI.Student.Fragments.ClassworkFragment
import com.asmanmirza.schoolpen.UI.Student.Fragments.CoursesFragment
import com.asmanmirza.schoolpen.UI.Student.Fragments.HomeFragment
import com.asmanmirza.schoolpen.UI.Student.Fragments.LearningFragment
import com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.notestab.AssignedClassNotesFragment
import com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.studentstab.AssignedClassStudentsFragment

class AssignedClassTabsAdapter (var fm: FragmentManager, var tabs:Int): FragmentPagerAdapter(fm, tabs) {

    override fun getCount(): Int {
        return tabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0->{
                AssignedClassStudentsFragment()
            }
            1->{
                AssignedClassNotesFragment()
            }
            else -> {
                AssignedClassStudentsFragment()
            }
        }
    }
}