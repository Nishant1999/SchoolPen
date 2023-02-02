package com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.Helpers.ZoomOutPageTransformer
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Teacher.Home.Adapters.AssignedClassTabsAdapter
import com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.notestab.NotesAdapter
import com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.notestab.NotesDataModel
import com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.studentstab.StudentAttendanceDataModel
import com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.studentstab.StudentAttendanceGridAdapter
import com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.studentstab.StudentDetailsDataModel
import com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.studentstab.StudentDetailsGridAdapter
import com.asmanmirza.schoolpen.databinding.ActivityAssignedClassBinding
import com.asmanmirza.schoolpen.databinding.ActivityLoginBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class AssignedClassActivity : AppCompatActivity() {

    lateinit var binding:ActivityAssignedClassBinding;
    var data : ArrayList<NotesDataModel> = ArrayList()
    lateinit var adapter: NotesAdapter;
    lateinit var details: ArrayList<StudentDetailsDataModel>
    lateinit var attendance: ArrayList<StudentAttendanceDataModel>
    lateinit var grades: ArrayList<String>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignedClassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {
            recChats.hide()
            tabLayout.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {

                    when(tab?.position){
                        0->{
                            recChats.hide()
                        }
                        1->{
                            recChats.show()
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

    fun RecyclerView.show(){
        binding.recChats.visibility = View.VISIBLE
        binding.layoutInputNote.visibility = View.VISIBLE
        binding.layoutStudent.visibility = View.GONE
        getNotes()
    }

    fun RecyclerView.hide(){
        binding.recChats.visibility = View.GONE
        binding.layoutInputNote.visibility = View.GONE
        binding.layoutStudent.visibility = View.VISIBLE
        getStudents()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun getNotes(){
        binding.recChats.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        if(data.isEmpty()) {
            data.add(
                NotesDataModel(
                    "Bring practical notebook to the class, we will conduct some experiments tomorrow.",
                    "today"
                )
            )
        }
        adapter = NotesAdapter(this, data)
        binding.recChats.adapter = adapter;

        binding.notesSendButton.setOnClickListener {
            if(binding.editTextNotes.text.isEmpty()){
                Toast.makeText(this@AssignedClassActivity, "Please enter a message", Toast.LENGTH_SHORT).show()
            }else{
                data.add(NotesDataModel(binding.editTextNotes.text.toString(), "today"))
                adapter.notifyDataSetChanged()
            }

        }
    }

    fun getStudents(){
        getDetails()
        binding.assignedClassStudentsTabLayout.getTabAt(0)?.select()
        binding.assignedClassStudentsTabLayout.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        getDetails()
                    }
                    1->{
                        getAttendance()
                    }
                    2->{
                        getGrades()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    fun getDetails(){
        details = ArrayList();
        binding.recStudentDetail.layoutManager = GridLayoutManager(this, 2)
        details.add(
            StudentDetailsDataModel(
                "Siddhant Sharma",
                "Vinod Sharma",
                20,
                "male",
                "Class V-C",
                R.drawable.fatima
            )
        )
        details.add(
            StudentDetailsDataModel(
                "Alex Jain",
                "Pradeep Jain",
                18,
                "male",
                "Class V-C",
                R.drawable.fatima
            )
        )
        details.add(
            StudentDetailsDataModel(
                "Sid Mehta",
                "Sonu Mehta",
                19,
                "male",
                "Class V-C",
                R.drawable.fatima
            )
        )
        details.add(
            StudentDetailsDataModel(
                "Nancy Gupta",
                "Rahul Gupta",
                18,
                "Female",
                "Class V-C",
                R.drawable.fatima
            )
        )
        details.add(
            StudentDetailsDataModel(
                "Mridul Kumawat",
                "Mahesh Kumawat",
                19,
                "male",
                "Class V-C",
                R.drawable.fatima
            )
        )
        binding.recStudentDetail.adapter = StudentDetailsGridAdapter(this, details)
    }

    fun getAttendance(){
        attendance = ArrayList()
        binding.recStudentDetail.layoutManager = GridLayoutManager(this, 2)
        attendance.add(
            StudentAttendanceDataModel(
                "Siddhant Sharma",
                "V-C",
                "85%",
                "10",
                "2",
                R.drawable.fatima
            )
        )
        attendance.add(
            StudentAttendanceDataModel(
                "Alex Jain",
                "V-C",
                "100%",
                "12",
                "0",
                R.drawable.fatima
            )
        )
        attendance.add(
            StudentAttendanceDataModel(
                "Nancy Gupta",
                "V-C",
                "100%",
                "12",
                "0",
                R.drawable.fatima
            )
        )
        attendance.add(
            StudentAttendanceDataModel(
                "Mridul Kumawat",
                "V-C",
                "65%",
                "8",
                "4",
                R.drawable.fatima
            )
        )
        attendance.add(
            StudentAttendanceDataModel(
                "Sid Mehta",
                "V-C",
                "70%",
                "9",
                "3",
                R.drawable.fatima
            )
        )
        binding.recStudentDetail.adapter = StudentAttendanceGridAdapter(this, attendance);
    }

    fun getGrades(){
        grades = ArrayList()
        binding.recStudentDetail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recStudentDetail.adapter = null
    }

}