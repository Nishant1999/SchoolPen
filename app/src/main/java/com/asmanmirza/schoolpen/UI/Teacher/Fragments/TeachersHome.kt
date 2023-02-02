package com.asmanmirza.schoolpen.UI.Teacher.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Adapters.AdapterEvents
import com.asmanmirza.schoolpen.Models.ModelDates
import com.asmanmirza.schoolpen.Models.ModelEvents
import com.asmanmirza.schoolpen.Adapters.AdapterHomeDates
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.UI.Teacher.Home.*
import com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.AssignedClassActivity
import com.asmanmirza.schoolpen.UI.Teacher.Profile.ProfileTeacherActivity
import com.asmanmirza.schoolpen.databinding.FragmentTeacherHomeBinding
import com.google.android.material.tabs.TabLayout


class TeachersHome : Fragment() {

    private var _binding: FragmentTeacherHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterHomeDates: AdapterHomeDates;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTeacherHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startClass.btnStartClass.setOnClickListener {
            navigateToAttendance()
        }

        binding.notificationClass.setOnClickListener{
            startActivity(Intent(requireContext(), TeacherLiveMeetingActivity::class.java))
        }

        binding.btnChat.setOnClickListener{
            startActivity(Intent(requireContext(), ChatHomeActivity::class.java))
        }
        binding.btnTimeTableToday.setOnClickListener {
            startActivity(Intent(requireContext(), TeacherTimeTableActivity::class.java))
        }
        binding.teachersLeavesTab.setOnClickListener {
            startActivity(Intent(requireContext(), TeachersLeaveActivity::class.java))
        }

        binding.assignedClass1.setOnClickListener {
            startActivity(Intent(requireContext(), AssignedClassActivity::class.java))
        }

        binding.homeDp1.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileTeacherActivity::class.java))
        }

        binding.ivSignout.setOnClickListener {
            val db = TinyDB(requireContext())
            db.clear()
            Toast.makeText(requireContext(), "Signed out successfully", Toast.LENGTH_SHORT).show()
            requireActivity().finishAffinity()
        }

        binding.assignedClass2.setOnClickListener {
            Toast.makeText(requireContext(), "You only can view first class.", Toast.LENGTH_SHORT).show()
        }
        binding.recDates.layoutManager = GridLayoutManager(requireContext(), 5)
        adapterHomeDates =  AdapterHomeDates(requireContext(), getDates(),"#C45162");
        binding.recDates.adapter = adapterHomeDates

        addEvents()

        binding.recEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        addEvents()
                    }
                    1 -> {
                        addTasks()
                    }
                    2 -> {
                        addBirthdays()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }
    private fun navigateToAttendance(){
        startActivity(Intent(requireContext(), StartClassIntroActivity::class.java))
    }

    fun getDates():ArrayList<ModelDates>{

        return ArrayList<ModelDates>().apply {
            add(ModelDates(9, "09/01/2023", false))
            add(ModelDates(10, "10/01/2023", false))
            add(ModelDates(11, "11/01/2023", false))
            add(ModelDates(12, "12/01/2023", false))
            add(ModelDates(13, "13/01/2023", false))
        }

    }
    fun addEvents(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(12, 1, "Annual sports meet", "All Day", "", "event"))
        events.add(ModelEvents(13, 1, "Award ceremony", "14:00-17:00", "", "event"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun addTasks(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(17, 1, "Ankita Sharma assignment", "05:00 PM", "High", "task"))
        events.add(ModelEvents(18, 1, "Read Ch-2 B V Ramana", "05:00 PM", "Med", "task"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun addBirthdays(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(23, 1, "Akhil's Birthday 🎂", "10:00 AM", "", "birthday"))
        events.add(ModelEvents(27, 1, "Principal Sir Birthday 🎂", "01:00 AM", "", "birthday"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }
}