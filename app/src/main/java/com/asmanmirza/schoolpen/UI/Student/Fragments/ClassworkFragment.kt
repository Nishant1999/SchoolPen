package com.asmanmirza.schoolpen.UI.Student.Fragments

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelTeacherRemarks
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelTest
import com.asmanmirza.schoolpen.databinding.FragmentClassworkBinding
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters.AdapterCompleteWork
import com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters.AdapterPendingHomework
import com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters.AdapterTeacherRemarks
import com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters.AdapterTest
import com.asmanmirza.schoolpen.UI.Student.Classwork.details.HomeWorkDetailActivity
import com.asmanmirza.schoolpen.UI.Student.Classwork.details.TestDetailActivity
import com.asmanmirza.schoolpen.UI.Student.Classwork.grades.CumulativeGradeActivity
import com.asmanmirza.schoolpen.UI.Student.Classwork.grades.TodaysGradeActivity
import com.asmanmirza.schoolpen.UI.Student.assignment.AssignmentHomeAct
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class ClassworkFragment : Fragment() {

    private var _binding: FragmentClassworkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClassworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        binding.recCompletedWork.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recPendingWork.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recCompletedWork.adapter = AdapterCompleteWork(requireContext(), ArrayList());
        binding.recPendingWork.adapter = AdapterPendingHomework(requireContext(), ArrayList());
    }

    fun getTestDetails(){
        binding.recPendingTest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recUpcomingTest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recPendingTest.adapter = AdapterTest(requireContext(), getPendingTest());
        binding.recUpcomingTest.adapter = AdapterTest(requireContext(), getUpcomingTest());
    }

    fun getScoreDetails(){
        binding.recTeacherRemarks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recTeacherRemarks.adapter = AdapterTeacherRemarks(requireContext(), getRemarks())
    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        binding.scrollView.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        return scrollBounds.top <= top && scrollBounds.bottom >= bottom
    }

    fun getPendingTest(): ArrayList<ModelTest>{
        return ArrayList<ModelTest>().apply{
            add(ModelTest("", "October Exam", "English", "29/10/2022", "100", "90 mins", R.drawable.abc, false))
        }
    }

    fun getUpcomingTest(): ArrayList<ModelTest>{
        return ArrayList<ModelTest>().apply {
            add(ModelTest("", "Unit test 1", "Science", "31/10/2022", "100", "90 mins", R.drawable.test_tube, true))
            add(ModelTest("", "MCQ Monthly", "English", "31/10/2022", "100", "90 mins", R.drawable.abc, true))
        }
    }

    fun getRemarks():ArrayList<ModelTeacherRemarks>{

        return ArrayList<ModelTeacherRemarks>().apply {
            add(ModelTeacherRemarks("", "Sonu Sharma", "You have to focus on your grammar, context is very good but lacks the way you write your answers.", "English", "1 hour ago", ""))
            add(ModelTeacherRemarks("", "Ujjwal Mittal", "Bring practical notebook to the class, we will conduct some experiments tomorrow.", "Physics", "4 hours ago", ""))
            add(ModelTeacherRemarks("", "D Kapoor", "Your progress is good, keep it up and do more hardwork", "Chemistry", "1 day ago", ""))

        }

    }

}