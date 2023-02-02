package com.asmanmirza.schoolpen.UI.Student.assignment.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.UI.Student.assignment.ZoomImageAct
import com.asmanmirza.schoolpen.databinding.ImgWithDescriptionAnsFrgmntBinding

class ImageWithDescriptonFrgmnt: Fragment(){

    private var _binding: ImgWithDescriptionAnsFrgmntBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ImgWithDescriptionAnsFrgmntBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.imgImgDescriptionAnsFrgmnt?.setOnClickListener {
            ZoomImageAct.startActivity(requireActivity())
        }
    }

}