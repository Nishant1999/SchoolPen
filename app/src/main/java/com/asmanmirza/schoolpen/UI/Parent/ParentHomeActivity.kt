package com.asmanmirza.schoolpen.UI.Parent

import android.app.Application
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Adapters.AdapterHomeTodaysClasses
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.Helpers.ZoomOutPageTransformer
import com.asmanmirza.schoolpen.Models.ModelClasses
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Parent.Adapters.AdapterFeeback
import com.asmanmirza.schoolpen.UI.Parent.Models.ModelFeedback
import com.asmanmirza.schoolpen.UI.Student.Courses.Adapters.AdapterCourses
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseDTO
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelCourse
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelHome
import com.asmanmirza.schoolpen.UI.Student.models.HomeViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.models.Period
import com.asmanmirza.schoolpen.UI.Student.repository.CourseRepo
import com.asmanmirza.schoolpen.UI.Student.repository.HomeRepository
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import com.asmanmirza.schoolpen.databinding.ActivityParentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import javax.inject.Inject

class ParentHomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityParentHomeBinding;
    lateinit var db: TinyDB;
    lateinit var myApi: MyApi
    lateinit var data: ArrayList<CourseDTO>
    lateinit var courseViewModel: ViewModelCourse

    lateinit var periodData: ArrayList<Period>
    lateinit var homeViewModel: ViewModelHome

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    @Inject
    lateinit var courseViewModelFactory: CourseViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        courseViewModelFactory= CourseViewModelFactory(CourseRepo(application))
        db = TinyDB(this)
        data=ArrayList()
        periodData=ArrayList()

        courseViewModel =
            ViewModelProvider(this, courseViewModelFactory)[ViewModelCourse::class.java]
        val token = db.getString("token")
        courseViewModel.getAllCourse(
            "Bearer $token"
        )

        homeViewModelFactory= HomeViewModelFactory(HomeRepository(application))

        homeViewModel =
            ViewModelProvider(this, homeViewModelFactory)[ViewModelHome::class.java]

        homeViewModel.getPeriodClassId("Bearer $token")

        updateData()
    }

    private fun updateData(){
        binding.apply {
            with(viewPagerHerClasses){
                homeViewModel.todayPeriodData.observe(this@ParentHomeActivity) {
                    periodData.addAll(it)
                    adapter = AdapterHomeTodaysClasses(
                        this@ParentHomeActivity,
                        periodData,
                        R.drawable.back_todays_classes_parent
                    )
                    setPageTransformer(true, ZoomOutPageTransformer())
                    dotsIndicator1.attachTo(this)
                }
            }

            getClass()
            recResumeCourses.layoutManager = LinearLayoutManager(this@ParentHomeActivity, LinearLayoutManager.HORIZONTAL, false)
            recTopCourses.layoutManager = LinearLayoutManager(this@ParentHomeActivity, LinearLayoutManager.HORIZONTAL, false)
            courseViewModel.courseData.observe(this@ParentHomeActivity) {
                data.addAll(it)
                recResumeCourses.adapter = AdapterCourses(this@ParentHomeActivity, data,1)
                recTopCourses.adapter = AdapterCourses(this@ParentHomeActivity,data,2)
            }

            btnLogout.setOnClickListener {
                val db = TinyDB(this@ParentHomeActivity)
                db.clear()
                Toast.makeText(this@ParentHomeActivity, "Signed out successfully", Toast.LENGTH_SHORT).show()
                finishAffinity()
            }
            tabMain.getTabAt(0)?.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
            tabMain.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)

                    when(tab?.position){
                        0->{
                            getClass()
                        }
                        1->{
                            getFeedBacks()
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.icon?.setColorFilter(Color.parseColor("#707070"), PorterDuff.Mode.SRC_IN)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })
        }
    }

    fun getFeedBacks(){
        binding.apply {
            layoutClass.visibility = View.GONE
            recFeedback.visibility = View.VISIBLE
            recFeedback.layoutManager = LinearLayoutManager(this@ParentHomeActivity,LinearLayoutManager.VERTICAL, false)
            recFeedback.adapter = AdapterFeeback(this@ParentHomeActivity, ArrayList<ModelFeedback>())
        }
    }


    fun getClass(){
       binding.apply {
           layoutClass.visibility = View.VISIBLE
           recFeedback.visibility = View.GONE
       }
    }

/*    fun getCourses():ArrayList<ModelCourses>{

        return ArrayList<ModelCourses>().apply {
            add(ModelCourses("", "Trigonometry", "Radian Measure, Triangle Solution, Amplitude, Solving Trigonometric Equation", "4.5", "499", "https://examsbook.co.in/img/post/large/DBmGTrigonometry-Important-Questions-for-Competitive-Exams.jpg"))
            add(ModelCourses("", "Artificial Intelligence", "Linear algebra and statistics, Signal processing techniques, Neural network architectures", "4.3", "1999", "https://img.freepik.com/free-vector/futuristic-ai-technology-template-vector-disruptive-technology-blog-banner_53876-117833.jpg"))
            add(ModelCourses("", "Inorganic Chemistry", "Analytical Methods, Chemistry, Icp-Ms, Water Quality", "4.4", "1299", "https://thumbs.dreamstime.com/b/inorganic-chemistry-vector-colorful-round-linear-illustration-inorganic-chemistry-vector-colorful-round-illustration-outline-142068644.jpg"))
            add(ModelCourses("", "English Speaking", "Easy Reading, Speaking and master in English Language", "4.5", "799", "https://image.shutterstock.com/image-vector/horizontal-internet-banner-learning-english-260nw-1604567167.jpg"))
            add(ModelCourses("", "History of India", "You will gain all the info about ancient buildings, temples and peoples.", "4.2", "1499", "https://static.independent.co.uk/2022/09/30/16/iStock-689331314.jpg"))
        }
    }*/

   /* fun getTodayClasses():ArrayList<ModelClasses>{
        return ArrayList<ModelClasses>().apply{
            add(ModelClasses("", "1", "Social Science", "Sonu Sharma", "Chapter 2: History of India", ""))
            add(ModelClasses("", "2", "Mathematics", "Alex Edward", "Chapter 3: Combination & Permutation", ""))
            add(ModelClasses("", "3", "Science", "Ashley Jain", "Chapter 6: Chemical & Solutions", ""))
            add(ModelClasses("", "4", "English", "Danny Mathur", "Chapter 2: Active and Passive Voices", ""))
            add(ModelClasses("", "5", "Hindi", "S Gupta", "Chapter 4: Story of Buddha", ""))
            add(ModelClasses("", "6", "Computer", "Taylor", "Chapter 2: Learning basic of computer", ""))
        }
    }*/
}