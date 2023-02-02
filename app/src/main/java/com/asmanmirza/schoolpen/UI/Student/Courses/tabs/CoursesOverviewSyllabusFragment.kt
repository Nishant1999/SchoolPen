package com.asmanmirza.schoolpen.presentation.main.coursesoverview.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentCoursesOverviewSyllabusBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CoursesOverviewSyllabusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoursesOverviewSyllabusFragment : Fragment() {

    var _binding : FragmentCoursesOverviewSyllabusBinding? = null
    val binding  get() = _binding!!



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dropDownButton.setOnClickListener {
            binding.dropDownButton.rotation = binding.dropDownButton.rotation + 180.0F
            if(binding.chapterContents.visibility == View.VISIBLE){
                binding.chapterContents.visibility = View.GONE
            }
            else{
                binding.chapterContents.visibility = View.VISIBLE
            }
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentCoursesOverviewSyllabusBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}