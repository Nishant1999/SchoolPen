package com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.studentstab

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentStudentDetailsBinding
import kotlinx.coroutines.launch
import java.util.*

class StudentDetailsFragment : Fragment(),onToggle {

    private var _binding: FragmentStudentDetailsBinding? = null
    private val binding get() = _binding!!
    var list = ArrayList<StudentDetailsDataModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStudentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewStudentDetailsGrid.layoutManager = GridLayoutManager(context, 2)
        list.add(
            StudentDetailsDataModel(
                "Siddhant Sharma",
                "Vinod Sharma",
                20,
                "male",
                "class none",
                R.drawable.fatima
            )
        )
        list.add(
            StudentDetailsDataModel(
                "Alex Jain",
                "Pradeep Jain",
                18,
                "male",
                "class none",
                R.drawable.fatima
            )
        )
        list.add(
            StudentDetailsDataModel(
                "Sid Mehta",
                "Sonu Mehta",
                19,
                "male",
                "class none",
                R.drawable.fatima
            )
        )
        list.add(
            StudentDetailsDataModel(
                "Nancy Gupta",
                "Rahul Gupta",
                18,
                "Female",
                "class none",
                R.drawable.fatima
            )
        )
        list.add(
            StudentDetailsDataModel(
                "Mridul Kumawat",
                "Mahesh Kumawat",
                19,
                "male",
                "class none",
                R.drawable.fatima
            )
        )

        binding.recyclerViewStudentDetailsGrid.adapter =
              StudentDetailsGridAdapter(requireContext(), list)
        binding.recyclerViewStudentDetailsList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewStudentDetailsList.adapter =
            StudentDetailsListAdapter(requireContext(), list)
        binding.recyclerViewStudentDetailsGrid.visibility = View.VISIBLE
    }


     override fun onTogggleClicked() {
        if (binding.recyclerViewStudentDetailsGrid.visibility == View.VISIBLE) {
            binding.recyclerViewStudentDetailsGrid.visibility = View.VISIBLE
            binding.recyclerViewStudentDetailsList.visibility = View.GONE
        } else {
            binding.recyclerViewStudentDetailsGrid.visibility = View.GONE
            binding.recyclerViewStudentDetailsList.visibility = View.VISIBLE
        }
    }
}
