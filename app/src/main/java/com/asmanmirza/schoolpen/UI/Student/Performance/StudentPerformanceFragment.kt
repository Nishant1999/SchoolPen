package com.asmanmirza.schoolpen.UI.Student.Performance

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentStudentPerformanceBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class StudentPerformanceFragment : Fragment() {

    private lateinit var viewBinding: FragmentStudentPerformanceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentStudentPerformanceBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.viewPagerContainer.adapter =
            StudentPerformanceTabsAdapter(parentFragmentManager, viewBinding.tabs.tabCount)

        viewBinding.apply {

            tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
//                    if(tab?.position != 0){
//                        tabs.selectTab(tabs.getTabAt(0))
//                        Toast.makeText(requireContext(), "You currently can't access the other tabs except 1st one.", Toast.LENGTH_LONG).show()
//                    }

                    when (tab?.position) {
                        0 -> {
                            Log.e("count", tab.position.toString())
                            viewPagerContainer.currentItem = 0
                        }
                        1 -> {
                            Log.e("count", tab.position.toString())
                            viewPagerContainer.currentItem = 1
                        }
                        2 -> {
                            Log.e("count", tab.position.toString())
                            viewPagerContainer.currentItem = 2
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