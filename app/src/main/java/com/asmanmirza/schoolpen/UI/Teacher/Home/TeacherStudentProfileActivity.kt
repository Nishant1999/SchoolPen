package com.asmanmirza.schoolpen.UI.Teacher.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Teacher.Home.Adapters.StudentAttendenceStatusAdapter
import com.asmanmirza.schoolpen.UI.Teacher.Home.Models.ModelStudentAttendanceStatus
import com.asmanmirza.schoolpen.databinding.ActivityTeacherStudentProfileBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class TeacherStudentProfileActivity : AppCompatActivity() {

    lateinit var binding:ActivityTeacherStudentProfileBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherStudentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    fun updateData(){

        binding.apply {

            binding.recAttendance.layoutManager = LinearLayoutManager(this@TeacherStudentProfileActivity, LinearLayoutManager.VERTICAL, false)
            binding.recAttendance.adapter = StudentAttendenceStatusAdapter(this@TeacherStudentProfileActivity, getClasses())

            tabs.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        0->{
                            selectLayout(0)
                        }
                        1->{
                            selectLayout(1)
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

    fun getClasses():ArrayList<ModelStudentAttendanceStatus>{

        return ArrayList<ModelStudentAttendanceStatus>().apply {
            add(ModelStudentAttendanceStatus("", " Class V"))
            add(ModelStudentAttendanceStatus("", " Class IV"))
            add(ModelStudentAttendanceStatus("", " Class III"))
            add(ModelStudentAttendanceStatus("", " Class II"))
            add(ModelStudentAttendanceStatus("", " Class I"))
        }

    }


    fun selectLayout(pos:Int){

        binding.apply {
            if (pos == 0) {
                layoutPersonalInfo.root.visibility = View.VISIBLE
            }else{
                layoutPersonalInfo.root.visibility = View.GONE
            }

            if(pos == 1){
                binding.recAttendance.visibility = View.VISIBLE
            }else{
                binding.recAttendance.visibility = View.GONE
            }

        }

    }


}