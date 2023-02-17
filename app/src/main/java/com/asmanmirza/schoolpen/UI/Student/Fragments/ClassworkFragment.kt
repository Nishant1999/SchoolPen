package com.asmanmirza.schoolpen.UI.Student.Fragments

import android.app.Application
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Helpers.ApiClient
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelTeacherRemarks
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelTest
import com.asmanmirza.schoolpen.databinding.FragmentClassworkBinding
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.Models.*
import com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters.AdapterCompleteWork
import com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters.AdapterPendingHomework
import com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters.AdapterTeacherRemarks
import com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters.AdapterTest
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.HomeWork
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.TestData
import com.asmanmirza.schoolpen.UI.Student.Classwork.details.HomeWorkDetailActivity
import com.asmanmirza.schoolpen.UI.Student.Classwork.details.TestDetailActivity
import com.asmanmirza.schoolpen.UI.Student.Classwork.grades.CumulativeGradeActivity
import com.asmanmirza.schoolpen.UI.Student.Classwork.grades.TodaysGradeActivity
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelHome
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelHomework
import com.asmanmirza.schoolpen.UI.Student.assignment.AssignmentHomeAct
import com.asmanmirza.schoolpen.UI.Student.models.HomeViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.models.HomeWorkViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.repository.HomeRepository
import com.asmanmirza.schoolpen.UI.Student.repository.HomeworkRepository
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ClassworkFragment : Fragment() {

    private var _binding: FragmentClassworkBinding? = null
    private val binding get() = _binding!!
    lateinit var db: TinyDB;
    lateinit var myApi:MyApi
    lateinit var data: ArrayList<HomeWork>
    lateinit var dataFeedbackStudent: ArrayList<FeedBackForStudentByTeacherDTO>
    lateinit var homeWorkViewModel: ViewModelHomework
    lateinit var testData:ArrayList<TestData>
    lateinit var token:String
    @Inject
    lateinit var homeWorkViewModelFactory: HomeWorkViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClassworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeWorkViewModelFactory= HomeWorkViewModelFactory(HomeworkRepository(context?.applicationContext as Application))
        db = TinyDB(requireContext())
        data=ArrayList()
        testData= ArrayList()
        dataFeedbackStudent=ArrayList()

        homeWorkViewModel =
            ViewModelProvider(this, homeWorkViewModelFactory)[ViewModelHomework::class.java]
        token = db.getString("token")
        homeWorkViewModel.getHomework(
            1,1,"Bearer $token"
        )

        binding.apply {
            getHomeworkDetails()
            /*MainActivity.instance.updateStatusBarColor("#259163D7")
            HostFragment.instance.hideBottomNavBar(0)*/
            todayGradeCard.setOnClickListener {
                startActivity(Intent(requireContext(), TodaysGradeActivity::class.java))
            }

            cumulativeGradeBtn.setOnClickListener {
                startActivity(Intent(requireContext(), CumulativeGradeActivity::class.java))
            }

            tabs.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){

                        0->{
                            getHomeworkDetails()
                            showView(layoutHomework, layoutTest, layoutScorecard)
                        }
                        1->{
                            getTestDetails()
                            showView(layoutTest, layoutHomework, layoutScorecard)
                        }
                        2->{
                            getScoreDetails()
                            showView(layoutScorecard, layoutHomework, layoutTest)
                        }

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })

            ItemClickSupport.addTo(recPendingWork).setOnItemClickListener { recyclerView, position, v ->
                startActivity(Intent(requireContext(), HomeWorkDetailActivity::class.java))
            }
            ItemClickSupport.addTo(recPendingTest).setOnItemClickListener { recyclerView, position, v ->
                startActivity(Intent(requireContext(), TestDetailActivity::class.java))
            }

            binding.resumeHw.flResumeHomeWork.setOnClickListener {
                AssignmentHomeAct.startActivity(requireActivity())
            }

        }
    }

    fun showView(v1:View, v2:View, v3:View){
        v1.visibility = View.VISIBLE;
        v2.visibility = View.GONE
        v3.visibility = View.GONE
    }

    fun getHomeworkDetails(){
/*        binding.recCompletedWork.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recPendingWork.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recCompletedWork.adapter = AdapterCompleteWork(requireContext(), ArrayList());*/


        homeWorkViewModel.homeworkData.observe(viewLifecycleOwner) {
            data.addAll(it)
            binding.recPendingWork.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recPendingWork.adapter = AdapterPendingHomework(requireContext(),data)
            binding.recCompletedWork.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recCompletedWork.adapter = AdapterCompleteWork(requireContext(), data);
        }

    }

    fun getTestDetails(){
        binding.recPendingTest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recUpcomingTest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        myApi = ApiClient.getClient()?.create(MyApi::class.java)!!

        myApi.getClassTestById(db.getString("classId").toDouble().toInt(),"Bearer"+" "+db.getString("token")).enqueue(object:Callback<ModelTest>{
            override fun onResponse(call: Call<ModelTest>, response: Response<ModelTest>) {

                if(response.isSuccessful) {

                    Log.d("+++respose",response.body().toString())
                    val test = response.body()?.data!!.tests as ArrayList<TestData>
                    for(i in test)
                    {
                        testData.add(i)
                    }
                }
                else{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_LONG).show()
                }

                binding.recPendingTest.adapter = AdapterTest(requireContext(),testData);
                binding.recUpcomingTest.adapter = AdapterTest(requireContext(), testData);
            }



            override fun onFailure(call: Call<ModelTest>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    fun getScoreDetails(){
        binding.recTeacherRemarks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val  myApi = ApiClient.getClient()?.create(MyApi::class.java)!!

        myApi.getFeedbackStudentId(1,"Bearer $token").enqueue(object :Callback<ModelFeedbackStudent>{
            override fun onResponse(
                call: Call<ModelFeedbackStudent>,
                response: Response<ModelFeedbackStudent>
            ) {
                if(response.isSuccessful) {
                    val d: DataFeedBackStudent? = response.body()!!.data

                    val remarksList:List<FeedBackForStudentByTeacherDTO> = d!!.feedBackForStudentByTeacherDTOs

                    dataFeedbackStudent.addAll(remarksList)
                    Log.d("++++remarks", dataFeedbackStudent.toString())
                    binding.recTeacherRemarks.adapter = AdapterTeacherRemarks(requireContext(), dataFeedbackStudent)

                }
            }

            override fun onFailure(call: Call<ModelFeedbackStudent>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        binding.scrollView.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        return scrollBounds.top <= top && scrollBounds.bottom >= bottom
    }

   /* fun getPendingTest(): ArrayList<ModelTest>{
        return ArrayList<ModelTest>().apply{
            add(ModelTest("", "October Exam", "English", "29/10/2022", "100", "90 mins", R.drawable.abc, false))
        }
    }*/

 /*   fun getUpcomingTest(): ArrayList<ModelTest>{
        return ArrayList<ModelTest>().apply {
            add(ModelTest("", "Unit test 1", "Science", "31/10/2022", "100", "90 mins", R.drawable.test_tube, true))
            add(ModelTest("", "MCQ Monthly", "English", "31/10/2022", "100", "90 mins", R.drawable.abc, true))
        }
    }*/

    fun getRemarks():ArrayList<ModelTeacherRemarks>{

        return ArrayList<ModelTeacherRemarks>().apply {
            add(ModelTeacherRemarks("", "Sonu Sharma", "You have to focus on your grammar, context is very good but lacks the way you write your answers.", "English", "1 hour ago", ""))
            add(ModelTeacherRemarks("", "Ujjwal Mittal", "Bring practical notebook to the class, we will conduct some experiments tomorrow.", "Physics", "4 hours ago", ""))
            add(ModelTeacherRemarks("", "D Kapoor", "Your progress is good, keep it up and do more hardwork", "Chemistry", "1 day ago", ""))

        }

    }

}