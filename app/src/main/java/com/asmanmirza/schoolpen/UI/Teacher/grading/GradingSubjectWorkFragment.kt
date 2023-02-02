package com.asmanmirza.schoolpen.UI.Teacher.grading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Teacher.grading.GradingStudentAnsweresFragment
import com.asmanmirza.schoolpen.UI.Teacher.grading.GradingSubjectWorkAdapter
import com.asmanmirza.schoolpen.UI.Teacher.grading.GradingSubjectWorkDataModel
import com.asmanmirza.schoolpen.UI.Teacher.grading.GradingWorkStudentItemClickEventHandler
import com.asmanmirza.schoolpen.databinding.FragmentGradingSubjectWorkBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GradingSubjectWorkFragment : Fragment(), GradingWorkStudentItemClickEventHandler {

    private var _binding: FragmentGradingSubjectWorkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGradingSubjectWorkBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.gradingWorkRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.gradingWorkRecyclerView.adapter = GradingSubjectWorkAdapter(requireContext(),getData(),this)

        binding.backButton.setOnClickListener {
            requireActivity().finish()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    fun getData(): ArrayList<GradingSubjectWorkDataModel>{
        var list= ArrayList<GradingSubjectWorkDataModel>()
        list.add(GradingSubjectWorkDataModel(studentDp = R.drawable.fatima, studentName = "Alex Sharma",submitDetails = true,grade = "5.6"))
        list.add(GradingSubjectWorkDataModel(studentDp = R.drawable.fatima, studentName = "Sonu Mathur",submitDetails = true,grade = "9.6"))
        list.add(GradingSubjectWorkDataModel(studentDp = R.drawable.fatima, studentName = "Ashley Jain",submitDetails = false))
        list.add(GradingSubjectWorkDataModel(studentDp = R.drawable.fatima, studentName = "Danny Mathur",submitDetails = true,grade = "5.2"))
        return list
    }

    override fun onClick(name: String) {
//        .beginTransaction().replace(R.id.grading_subject_work_fragment_container,GradingStudentAnsweresFragment()).commit()
        parentFragmentManager.beginTransaction().replace(R.id.grading_subject_work_fragment_container,
            GradingStudentAnsweresFragment()
        ).commitNow()
    }

}