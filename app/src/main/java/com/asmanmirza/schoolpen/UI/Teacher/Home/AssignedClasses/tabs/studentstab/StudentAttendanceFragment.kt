package com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.studentstab

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentStudentAttendanceBinding
import kotlinx.coroutines.launch
import java.util.*


class StudentAttendanceFragment : Fragment(),onToggle {

    private var _binding: FragmentStudentAttendanceBinding? = null
    private val binding get() = _binding!!
    var list = ArrayList<StudentAttendanceDataModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStudentAttendanceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewStudentAttendanceGrid.layoutManager = GridLayoutManager(context, 2)
        list.add(
            StudentAttendanceDataModel(
                "Siddhant Sharma",
                "V-C",
                "20%",
                "10",
                "2",
                R.drawable.fatima
            )
        )
        list.add(
            StudentAttendanceDataModel(
                "Alex Jain",
                "V-C",
                "20%",
                "12",
                "0",
                R.drawable.fatima
            )
        )
        list.add(
            StudentAttendanceDataModel(
                "Nancy Gupta",
                "V-C",
                "20%",
                "12",
                "0",
                R.drawable.fatima
            )
        )
        list.add(
            StudentAttendanceDataModel(
                "Mridul Kumawat",
                "V-C",
                "20%",
                "8",
                "4",
                R.drawable.fatima
            )
        )
        list.add(
            StudentAttendanceDataModel(
                "Sid Mehta",
                "V-C",
                "20%",
                "9",
                "3",
                R.drawable.fatima
            )
        )

        binding.recyclerViewStudentAttendanceGrid.adapter =
            StudentAttendanceGridAdapter(requireContext(), list)
        binding.recyclerViewStudentAttendanceList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        binding.recyclerViewStudentAttendanceList.adapter =
            StudentAttendanceListListAdapter(requireContext(), list)

        binding.recyclerViewStudentAttendanceGrid.visibility = View.VISIBLE


    }


    override fun onTogggleClicked() {
        if (binding.recyclerViewStudentAttendanceGrid.visibility == View.VISIBLE) {
            binding.recyclerViewStudentAttendanceGrid.visibility = View.VISIBLE
            binding.recyclerViewStudentAttendanceList.visibility = View.GONE
        } else {
            binding.recyclerViewStudentAttendanceGrid.visibility = View.GONE
            binding.recyclerViewStudentAttendanceList.visibility = View.VISIBLE
        }
    }

}