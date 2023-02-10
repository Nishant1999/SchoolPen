package com.asmanmirza.schoolpen.presentation.main.coursesoverview.tabs

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelCourse
import com.asmanmirza.schoolpen.UI.Student.repository.CourseRepo
import com.asmanmirza.schoolpen.databinding.FragmentCourseOverviewAboutCourseBinding
import javax.inject.Inject


class CourseOverviewAboutCourseFragment(id:Int) : Fragment() {

    private var _binding: FragmentCourseOverviewAboutCourseBinding? = null
    private val binding get() = _binding!!
    lateinit var db: TinyDB;
    private lateinit var viewModelCourse: ViewModelCourse
    private var orderId: Int = id


    @Inject
    lateinit var courseViewModelFactory: CourseViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCourseOverviewAboutCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

   /* companion object {

        *//**
         * Method used to get the instance of the fragment.
         *
         * @return fragment instance
         *//*
        @JvmStatic
        fun getInstance(orderId: Int): CourseOverviewAboutCourseFragment {
            val fragment = CourseOverviewAboutCourseFragment()
            val bundle = Bundle()
            bundle.putInt("orderId", orderId)
            fragment.arguments = bundle
            return fragment
        }
*/
            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("++++Fragemtn id",""+id)
        courseViewModelFactory= CourseViewModelFactory(CourseRepo(context?.applicationContext as Application))
        db = TinyDB(context)
        viewModelCourse =
            ViewModelProvider(this, courseViewModelFactory)[ViewModelCourse::class.java]

        val token = db.getString("token")
        viewModelCourse.getCourseId(
            orderId, "Bearer $token"
        )

        binding.apply {
            viewModelCourse.courseDataId.observe(viewLifecycleOwner) {

                binding.textAboutCourseContent.text=it.aboutCourse
                binding.textRequirementsContent.text=it.requirement
            }
        }


    }



}