package com.asmanmirza.schoolpen.presentation.main.coursesoverview.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asmanmirza.schoolpen.R

/**
 * A simple [Fragment] subclass.
 * Use the [CoursesOverviewReviewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoursesOverviewReviewsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses_overview_reviews, container, false)
    }


}