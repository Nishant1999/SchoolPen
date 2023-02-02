package com.asmanmirza.schoolpen.UI.Teacher.grading.tabs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.databinding.FragmentGradingHomeworkTabBinding
import com.asmanmirza.schoolpen.UI.Teacher.grading.GradingSubjectWorkActivity
import java.util.*

class GradingHomeworkTabFragment : Fragment(), GradingHomeWorkClickEventHandler {

    private var _binding : FragmentGradingHomeworkTabBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGradingHomeworkTabBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recLatestHomework.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.recLatestHomework.adapter = HomeWorkAdapter(requireContext(),getLatestHomeWorkData(), this)

        binding.recOldHomework.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.recOldHomework.adapter = HomeWorkAdapter(requireContext(),getPreviousHomeWorkData(), this)

    }

    private fun getLatestHomeWorkData(): ArrayList<HomeWorkDataModel>{
        var list = ArrayList<HomeWorkDataModel>()
        list.add(HomeWorkDataModel(subjectName = "Science",sentDate = "Sent on 22/10/2022",chapterName = "Digestive System",task= "Write whats wrong with human body",gradedStudents = "32 stundents graded",newSubmissions = "4 new submissions"))
        list.add(HomeWorkDataModel(subjectName = "Science",sentDate = "Sent on 22/09/2022",chapterName = "Nervous System",task= "Write whats wrong with human mind",gradedStudents = "39 stundents graded",newSubmissions = "1 new submissions"))
        list.add(HomeWorkDataModel(subjectName = "Science",sentDate = "Sent on 22/11/2022",chapterName = "Animal Cells",task= "Write whats wrong with animal body",gradedStudents = "30 stundents graded",newSubmissions = "7 new submissions"))
        return list
    }

    private fun getPreviousHomeWorkData(): ArrayList<HomeWorkDataModel>{
        var list = ArrayList<HomeWorkDataModel>()
        list.add(HomeWorkDataModel(subjectName = "Mathematics",sentDate = "Sent on 12/10/2022",chapterName = "Number System",task= "What are whole numbers",gradedStudents = "30 stundents graded",newSubmissions = "4 new submissions"))
        list.add(HomeWorkDataModel(subjectName = "Mathematics",sentDate = "Sent on 22/09/2022",chapterName = "Integer System",task= "what are integers",gradedStudents = "39 stundents graded",newSubmissions = "1 new submissions"))
        list.add(HomeWorkDataModel(subjectName = "Mathematics",sentDate = "Sent on 02/11/2022",chapterName = "Trignometry",task= "what is maths",gradedStudents = "30 stundents gwraded",newSubmissions = "7 new submissions"))
        return list
    }

    override fun onClick(position: Int) {
        Log.d("launching","launching")
        startActivity(Intent(requireContext(), GradingSubjectWorkActivity::class.java))
    }


}