package com.asmanmirza.schoolpen.UI.Student.Fragments

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.databinding.FragmentCoursesBinding
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.UI.Student.Courses.Adapters.AdapterCourses
import com.asmanmirza.schoolpen.UI.Student.Courses.CourseDetailActivity
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.ModelCourses
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class CoursesFragment : Fragment() {

    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instance = this
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //HostFragment.instance.hideMenuIcon(true)
            recResumeCourses.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recTopCourses.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recFriendCourses.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recResumeCourses.adapter = AdapterCourses(requireContext(), getCourses())
            recTopCourses.adapter = AdapterCourses(requireContext(), getCourses())
            recFriendCourses.adapter = AdapterCourses(requireContext(), getCourses())
            scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if(isViewVisible(inSearch)){
                    floatingSearchButton.visibility = View.GONE
                }else{
                    floatingSearchButton.visibility = View.VISIBLE
                }
            })

            tabSubjects.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {

                    if(tab?.position != 0){
                        Toast.makeText(requireContext(), "You only can view all courses at this time.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

            ItemClickSupport.addTo(recTopCourses).setOnItemClickListener { recyclerView, position, v ->
                if(position == 0){
                    startActivity(Intent(requireContext(), CourseDetailActivity::class.java))
                }else{
                    Toast.makeText(requireContext(), "You only can view the first course.", Toast.LENGTH_SHORT).show()
                }
            }
            ItemClickSupport.addTo(recResumeCourses).setOnItemClickListener { recyclerView, position, v ->
                Toast.makeText(requireContext(), "You can't view this section", Toast.LENGTH_SHORT).show()
            }
            ItemClickSupport.addTo(recFriendCourses).setOnItemClickListener { recyclerView, position, v ->
                Toast.makeText(requireContext(), "You can't view this section", Toast.LENGTH_SHORT).show()
            }
            floatingSearchButton.setOnClickListener {
                scrollView.smoothScrollTo(0, 0)
            }
        }
    }

    fun getCourses():ArrayList<ModelCourses>{

        return ArrayList<ModelCourses>().apply {
            add(ModelCourses("", "Trigonometry", "Radian Measure, Triangle Solution, Amplitude, Solving Trigonometric Equation", "4.5", "499", "https://examsbook.co.in/img/post/large/DBmGTrigonometry-Important-Questions-for-Competitive-Exams.jpg"))
            add(ModelCourses("", "Artificial Intelligence", "Linear algebra and statistics, Signal processing techniques, Neural network architectures", "4.3", "1999", "https://img.freepik.com/free-vector/futuristic-ai-technology-template-vector-disruptive-technology-blog-banner_53876-117833.jpg"))
            add(ModelCourses("", "Inorganic Chemistry", "Analytical Methods, Chemistry, Icp-Ms, Water Quality", "4.4", "1299", "https://thumbs.dreamstime.com/b/inorganic-chemistry-vector-colorful-round-linear-illustration-inorganic-chemistry-vector-colorful-round-illustration-outline-142068644.jpg"))
            add(ModelCourses("", "English Speaking", "Easy Reading, Speaking and master in English Language", "4.5", "799", "https://image.shutterstock.com/image-vector/horizontal-internet-banner-learning-english-260nw-1604567167.jpg"))
            add(ModelCourses("", "History of India", "You will gain all the info about ancient buildings, temples and peoples.", "4.2", "1499", "https://static.independent.co.uk/2022/09/30/16/iStock-689331314.jpg"))
        }
    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        binding.scrollView.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        return scrollBounds.top <= top && scrollBounds.bottom >= bottom
    }

    fun scrollToTop(){
        binding.scrollView.fullScroll(NestedScrollView.FOCUS_UP)
    }

    companion object {
        lateinit var instance: CoursesFragment
            private set
    }
}