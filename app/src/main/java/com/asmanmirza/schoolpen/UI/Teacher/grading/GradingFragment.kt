package com.asmanmirza.schoolpen.UI.Teacher.grading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentGradingBinding

class GradingFragment : Fragment() {

    private var _binding : FragmentGradingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGradingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btnBack.setOnClickListener {

                requireActivity().finish()

            }

        }

    }

}