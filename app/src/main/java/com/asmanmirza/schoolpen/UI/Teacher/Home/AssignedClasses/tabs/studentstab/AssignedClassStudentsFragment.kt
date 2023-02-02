package com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.studentstab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.asmanmirza.schoolpen.Helpers.ZoomOutPageTransformer
import com.asmanmirza.schoolpen.UI.Teacher.Home.Models.AssignedClassSharedViewModel
import com.asmanmirza.schoolpen.databinding.FragmentAssignedClassStudentsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.ref.WeakReference


class AssignedClassStudentsFragment : Fragment() {

    private var _binding : FragmentAssignedClassStudentsBinding? =null
    private val binding get() = _binding!!
    private var _sharedViewModel: AssignedClassSharedViewModel? = null
    private val sharedViewModel get() = _sharedViewModel!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _sharedViewModel = ViewModelProvider(this).get(AssignedClassSharedViewModel::class.java)
        _binding = FragmentAssignedClassStudentsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = AssignedClassStudentsTabAdapter(parentFragmentManager,binding.assignedClassStudentsTabLayout.tabCount)
        binding.apply {
            assignedClassStudentsTabLayout.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        0->{
                          viewPager.currentItem = 0
                        }
                        1->{
                            viewPager.currentItem = 1
                        }
                        2->{
                            viewPager.currentItem = 2
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }


            })

        }

    }

}

interface onToggle{
    fun onTogggleClicked()
}