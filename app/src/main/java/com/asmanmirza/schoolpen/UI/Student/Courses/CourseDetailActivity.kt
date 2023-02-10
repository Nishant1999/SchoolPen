package com.asmanmirza.schoolpen.UI.Student.Courses

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Courses.Adapters.TabsAdapter
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseReviewModelFactory
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.Fee.viewModels.ViewModelProfile
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelCourse
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelReviewCourse
import com.asmanmirza.schoolpen.UI.Student.models.HomeViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.models.MainViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.repository.CourseRepo
import com.asmanmirza.schoolpen.UI.Student.repository.HomeRepository
import com.asmanmirza.schoolpen.databinding.ActivityCourseDetailBinding
import com.asmanmirza.schoolpen.databinding.ItemCourseDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator
import okhttp3.internal.notify
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class CourseDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityCourseDetailBinding;
    lateinit var itemBinding:ItemCourseDetailsBinding;

    lateinit var db: TinyDB;
    private lateinit var viewModelCourse: ViewModelCourse
    var id:Int = 0
    private lateinit var viewModelReviewCourse: ViewModelReviewCourse

    @Inject
    lateinit var courseReviewModelFactory: CourseReviewModelFactory

    @Inject
    lateinit var courseViewModelFactory: CourseViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)



        courseViewModelFactory= CourseViewModelFactory(CourseRepo(application))
        db = TinyDB(this)
        viewModelCourse =
            ViewModelProvider(this, courseViewModelFactory)[ViewModelCourse::class.java]
        id=intent.getIntExtra("id",0)
        val token = db.getString("token")
        viewModelCourse.getCourseId(
            id, "Bearer $token"
        )

        viewModelCourse.courseDataId.observe(this){

            Log.d("++++courseModel",it.toString())

            binding.courseName.text = it.courseName
            binding.courseOverviewDescriptionText.text =it.courseTitle


            binding.courseOverviewDetails.courseFee.text=it.courseFee.toString()

            binding.courseOverviewDetails.duration.text=it.courseDurationInWeeks

            binding.courseOverviewDetails.difficulty.text=it.dificultyLevel


            Log.d("+++++CourseFee",binding.courseOverviewDetails.courseFee.text.toString())
        }


       /* courseReviewModelFactory= CourseReviewModelFactory(CourseRepo(application))


        viewModelReviewCourse.getCourseReview(
            id, "Bearer $token"
        )*/

       /* binding.apply {
            var rating:Double=0.0
            viewModelReviewCourse.courseReviewData.observe(this@CourseDetailActivity){

                for(i in it){
                    rating+=i.feedBackStar
                }

                val averageRating:Double=rating/it.size

                val df = DecimalFormat("#.#")
                df.roundingMode = RoundingMode.CEILING

                if(it.size==1){
                    binding.
                }
                else {
                    binding.ra
                }
                Log.d("+++++courseDtoReview",""+it.size)

            }*/

        Toast.makeText(this,""+id,Toast.LENGTH_LONG).show()

        updateData(id)
    }

    fun updateData(id:Int){

        binding.apply {


           btnBack.setOnClickListener{
               finish()
            }
            viewPager.adapter = TabsAdapter(supportFragmentManager,lifecycle,id)
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

            Log.d("+++++id",""+id)
        }

    }

}