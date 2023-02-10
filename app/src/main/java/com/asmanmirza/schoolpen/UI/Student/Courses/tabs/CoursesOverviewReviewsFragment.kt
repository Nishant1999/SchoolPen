package com.asmanmirza.schoolpen.presentation.main.coursesoverview.tabs

import android.app.Application
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.Models.TodayliveClassDtos
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Courses.Adapters.CourseReview
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseDTO
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseReviewDTO
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseReviewModelFactory
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelCourse
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelReviewCourse
import com.asmanmirza.schoolpen.UI.Student.repository.CourseRepo
import com.asmanmirza.schoolpen.databinding.FragmentCourseOverviewAboutCourseBinding
import com.asmanmirza.schoolpen.databinding.FragmentCoursesOverviewReviewsBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [CoursesOverviewReviewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoursesOverviewReviewsFragment(id:Int) : Fragment() {

    private var orderId: Int = id
    private var _binding: FragmentCoursesOverviewReviewsBinding? = null
    private val binding get() = _binding!!
    lateinit var db: TinyDB;

    lateinit var data: ArrayList<CourseReviewDTO>


    private lateinit var viewModelReviewCourse: ViewModelReviewCourse

    @Inject
    lateinit var courseViewModelFactory: CourseReviewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCoursesOverviewReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Log.d("++++Fragemtn id",""+id)

        data= ArrayList()
        courseViewModelFactory= CourseReviewModelFactory(CourseRepo(context?.applicationContext as Application))
        db = TinyDB(context)

        viewModelReviewCourse =
            ViewModelProvider(this, courseViewModelFactory)[ViewModelReviewCourse::class.java]

        val token = db.getString("token")
        viewModelReviewCourse.getCourseReview(
            orderId, "Bearer $token"
        )

        binding.apply {
            var rating:Double=0.0
            binding.reviewRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            viewModelReviewCourse.courseReviewData.observe(viewLifecycleOwner){


                for(i in it){
                    rating+=i.feedBackStar
                }

                val averageRating:Double=rating/it.size

                val df = DecimalFormat("#.#")
                df.roundingMode = RoundingMode.CEILING

                val s:String=df.format(averageRating)
                if(it.size==1){
                    binding.ratingPoints.text= s+".0"
                }
                else {
                    binding.ratingPoints.text = s
                }
                val progress: Drawable = binding.ratingBar.progressDrawable
                val i:Int=s.toDouble().toInt()
                binding.ratingBar.stepSize=0.5f
                binding.ratingBar.rating= i.toFloat()

                DrawableCompat.setTint(progress, ContextCompat.getColor(requireContext(),R.color.stars_color))

                data.addAll(it)
                binding.reviewRecyclerView.adapter=CourseReview(requireContext(),data)

            }
        }
    }
}