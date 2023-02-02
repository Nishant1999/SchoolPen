package com.asmanmirza.schoolpen.UI.Student.Performance

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.asmanmirza.schoolpen.UI.Student.Fragments.ClassworkFragment
import com.asmanmirza.schoolpen.UI.Student.Fragments.CoursesFragment
import com.asmanmirza.schoolpen.UI.Student.Fragments.HomeFragment
import com.asmanmirza.schoolpen.UI.Student.Fragments.LearningFragment
import com.asmanmirza.schoolpen.UI.Student.Performance.tabs.PerformanceAcademicFragment
import com.asmanmirza.schoolpen.UI.Student.Performance.tabs.PerformanceConceptualFragmnet
import com.asmanmirza.schoolpen.UI.Student.Performance.tabs.PerformanceLearningsFragmnet

class StudentPerformanceTabsAdapter(private val fm: FragmentManager, private val tabs: Int) :
    FragmentPagerAdapter(fm, tabs) {
    override fun getCount(): Int {
        return tabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                PerformanceAcademicFragment()
            }
            1 -> {
                PerformanceConceptualFragmnet()
            }
            2 -> {
                PerformanceLearningsFragmnet()
            }
            else -> {
                PerformanceAcademicFragment()
            }
        }
    }
}