package com.asmanmirza.schoolpen.UI.Teacher.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Teacher.Fragments.Adapter.HomeWorkAdapter
import com.asmanmirza.schoolpen.UI.Teacher.Fragments.Adapter.HomeWorkDataModel
import com.asmanmirza.schoolpen.UI.Teacher.grading.GardingActivity
import com.asmanmirza.schoolpen.UI.Teacher.grading.GradingSubjectWorkActivity
import com.asmanmirza.schoolpen.databinding.FragmentTeacherClassworkBinding


class TeacherClassworkFragment : Fragment() {

    private var _binding: FragmentTeacherClassworkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeacherClassworkBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            binding.recLatestHomework.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            binding.recLatestHomework.adapter = HomeWorkAdapter(requireContext(),getLatestHomeWorkData())

            binding.recOldHomework.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            binding.recOldHomework.adapter = HomeWorkAdapter(requireContext(),getPreviousHomeWorkData())

            ItemClickSupport.addTo(recLatestHomework).setOnItemClickListener(object:OnItemClickListener{
                override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {

                    if(position == 0){
                        startActivity(Intent(requireContext(), GradingSubjectWorkActivity::class.java))
                    }else{
                        Toast.makeText(requireContext(), "Access Denied, you can access only first homework currently.", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            backButton.setOnClickListener {
                com.asmanmirza.schoolpen.UI.Teacher.TeachersHome.instance.setViewPagerPosition(0)
            }
        }
    }


    private fun getLatestHomeWorkData(): ArrayList<HomeWorkDataModel>{
        var list = ArrayList<HomeWorkDataModel>()
        list.add(HomeWorkDataModel(subjectName = "Science",sentDate = "Sent on 22/10/2022",chapterName = "Digestive System",task= "Write the parts of the digestive system expalining thr functions",gradedStudents = "32 stundents graded",newSubmissions = "4 new submissions"))
        list.add(HomeWorkDataModel(subjectName = "Science",sentDate = "Sent on 28/10/2022",chapterName = "Nervous System",task= "The ANS can be further subdivided into the sympathetic, parasympathetic, and enteric nervous systems.",gradedStudents = "39 stundents graded",newSubmissions = "1 new submissions"))
        list.add(HomeWorkDataModel(subjectName = "Science",sentDate = "Sent on 10/11/2022",chapterName = "Animal Cells",task= "As stated before, animal cells are eukaryotic cells with a membrane-bound nucleus.",gradedStudents = "30 stundents graded",newSubmissions = "7 new submissions"))
        return list
    }

    private fun getPreviousHomeWorkData(): ArrayList<HomeWorkDataModel>{
        var list = ArrayList<HomeWorkDataModel>()
        list.add(HomeWorkDataModel(subjectName = "Science",sentDate = "Sent on 02/11/2022",chapterName = "Chemical Names",task= "The name of a chemical compound that shows the names of each of its elements or subcompounds",gradedStudents = "Completed",newSubmissions = "All Submitted"))
        list.add(HomeWorkDataModel(subjectName = "Science",sentDate = "Sent on 12/10/2022",chapterName = "Chemical Names",task= "The name of a chemical compound that shows the names of each of its elements or subcompounds",gradedStudents = "Completed",newSubmissions = "All Submitted"))
        list.add(HomeWorkDataModel(subjectName = "Science",sentDate = "Sent on 22/09/2022",chapterName = "Chemical Names",task= "The name of a chemical compound that shows the names of each of its elements or subcompounds",gradedStudents = "Completed",newSubmissions = "All Submitted"))
        return list
    }

}