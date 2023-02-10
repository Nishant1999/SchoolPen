package com.asmanmirza.schoolpen.UI.Student.Courses.Adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asmanmirza.schoolpen.presentation.main.coursesoverview.tabs.*


class TabsAdapter(manager: FragmentManager, lifecycle:Lifecycle,id:Int) : FragmentStateAdapter(manager,lifecycle) {

    val courseId=id

    override fun getItemCount()= 5

    override fun createFragment(position: Int): Fragment {

        Log.d("+++courseId",""+courseId)
        return when(position){

            0 -> CourseOverviewAboutCourseFragment(courseId)
            1 -> CoursesOverviewSyllabusFragment()
            2 -> CoursesOverViewInstructorFragment(courseId)
            3 -> CoursesOverviewReviewsFragment(courseId)
            4 -> CourseOverviewCertificateFragmentFragment()
            else -> CourseOverviewCertificateFragmentFragment()
        }
    }


}