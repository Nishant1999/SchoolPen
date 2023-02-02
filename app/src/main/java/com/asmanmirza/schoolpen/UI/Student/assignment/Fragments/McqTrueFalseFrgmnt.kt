package com.asmanmirza.schoolpen.UI.Student.assignment.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.McqMultipleSelectionFrgmntBinding
import com.asmanmirza.schoolpen.databinding.McqSingleSelectionFrgmntBinding
import com.asmanmirza.schoolpen.databinding.McqTrueFalseSelectionFrgmntBinding

class McqTrueFalseFrgmnt: Fragment(){

    private var _binding: McqTrueFalseSelectionFrgmntBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = McqTrueFalseSelectionFrgmntBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}