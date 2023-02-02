package com.asmanmirza.schoolpen.UI.Student.assignment.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.databinding.SubjectiveOrPhotoUploadFrgmntBinding

class AnswerPhotoUploadFrgmnt: Fragment() {

    private var _binding: SubjectiveOrPhotoUploadFrgmntBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SubjectiveOrPhotoUploadFrgmntBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}