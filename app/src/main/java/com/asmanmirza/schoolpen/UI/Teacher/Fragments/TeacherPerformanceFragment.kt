package com.asmanmirza.schoolpen.UI.Teacher.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentTeacherClassworkBinding
import com.asmanmirza.schoolpen.databinding.FragmentTeacherPerforrmanceBinding


class TeacherPerformanceFragment : Fragment() {

    lateinit var binding: FragmentTeacherPerforrmanceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeacherPerforrmanceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}