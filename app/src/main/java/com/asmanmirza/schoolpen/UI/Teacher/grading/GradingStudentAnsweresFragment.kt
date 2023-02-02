package com.asmanmirza.schoolpen.UI.Teacher.grading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.databinding.FragmentGradingStudentAnsweresBinding
import java.util.*

class GradingStudentAnsweresFragment : Fragment() {

    private var _binding: FragmentGradingStudentAnsweresBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentGradingStudentAnsweresBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gradingStudentWorkRecycylerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.gradingStudentWorkRecycylerView.adapter = StudentsAnswerAdapter(requireContext(),getAnswers())
        binding.apply {
            backButton.setOnClickListener {
                requireActivity().finish()
            }
        }

    }

    fun getAnswers(): ArrayList<StudentsAnswerDataModel>{
        var list = ArrayList<StudentsAnswerDataModel>()
        list.add(StudentsAnswerDataModel(question = "Q1: The enzymes present in pancreatic juice are?",time="01:42 mins",maxMarks = "4","Trypsinogen, Lipase, Amylase",isObjective = false))
        list.add(StudentsAnswerDataModel(question = "Q1: The absorption of fructose by intestinal mucosa is?",time="01:42 mins",maxMarks = "4","facilitated transport",isObjective = false))
        list.add(StudentsAnswerDataModel(question = "Q1: Carboxypeptidase requires _______ for its activity?",time="01:42 mins",maxMarks = "4",isObjective = true,isNotCorrect = true, selectedOption = "C) Incorrect Option",correctOption = "A) Correct Option"))
        list.add(StudentsAnswerDataModel(question = "Q1: Secretin and cholecystokinin are secreted in?",time="01:42 mins",maxMarks = "4",isObjective = true,isNotCorrect = false, correctOption = "D) Correct Option"))
        return list
    }

}