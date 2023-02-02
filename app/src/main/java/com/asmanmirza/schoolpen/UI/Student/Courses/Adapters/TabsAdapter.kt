package com.asmanmirza.schoolpen.UI.Student.Courses.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asmanmirza.schoolpen.presentation.main.coursesoverview.tabs.*

class TabsAdapter(manager: FragmentManager, lifecycle:Lifecycle) : FragmentStateAdapter(manager,lifecycle) {
    override fun getItemCount()= 5

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> CourseOverviewAboutCourseFragment()
        1 -> CoursesOverviewSyllabusFragment()
        2 -> CoursesOverViewInstructorFragment()
        3 -> CoursesOverviewReviewsFragment()
        4 -> CourseOverviewCertificateFragmentFragment()
        else -> CourseOverviewCertificateFragmentFragment()
    }

}