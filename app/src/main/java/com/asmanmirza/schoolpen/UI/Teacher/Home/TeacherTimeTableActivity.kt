package com.asmanmirza.schoolpen.UI.Teacher.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Adapters.AdapterWeekDays
import com.asmanmirza.schoolpen.databinding.ActivityTeacherTimeTableBinding
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.presentation.main.Teacher.timetable.AdapterTimeTable
import com.asmanmirza.schoolpen.presentation.main.Teacher.timetable.ModelTimeTable

import java.util.ArrayList

class TeacherTimeTableActivity : AppCompatActivity() {

    lateinit var binding:ActivityTeacherTimeTableBinding
    lateinit var adapterWeekDays: AdapterWeekDays

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherTimeTableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {

            btnBack.setOnClickListener {
                finish()
            }

            recWeekDays.layoutManager =
                LinearLayoutManager(this@TeacherTimeTableActivity, LinearLayoutManager.HORIZONTAL, false)
            adapterWeekDays = AdapterWeekDays(this@TeacherTimeTableActivity, getWeekDayNames())
            recWeekDays.adapter = adapterWeekDays

            recTimeTable.layoutManager =
                LinearLayoutManager(this@TeacherTimeTableActivity, LinearLayoutManager.VERTICAL, false)
            recTimeTable.adapter = AdapterTimeTable(this@TeacherTimeTableActivity, getTimeTable())

            ItemClickSupport.addTo(recWeekDays).setOnItemClickListener { recyclerView, position, v ->
                adapterWeekDays.selected = position.toString()
                adapterWeekDays.notifyDataSetChanged()
            }

        }

    }

    fun getTimeTable(): ArrayList<ModelTimeTable> {
        return ArrayList<ModelTimeTable>().apply {

            add(ModelTimeTable("", "Period 1", "Class V-C", "Science", "8:00", "8:40", false))
            add(ModelTimeTable("", "Period 2", "Class III", "Science", "8:40", "9:20", false))
            add(ModelTimeTable("", "Period 3", "Class VI-A", "Science", "9:20", "10:00", false))
            add(ModelTimeTable("", "Period 4", "Class VII-A", "Science", "10:00", "10:40", false))
            add(ModelTimeTable("", "Period 5", "Class V-B", "Science", "10:40", "11:20", false))
            add(ModelTimeTable("", "", "", "", "11:20", "12:00", true))
            add(ModelTimeTable("", "Period 6", "Class VI-A", "Science", "12:10", "12:40", false))
            add(ModelTimeTable("", "Period 7", "Class IV-C", "Science", "12:40", "1:20", false))
            add(ModelTimeTable("", "Period 7", "Class VII-B", "Science", "1:20", "2:00", false))

        }
    }

    fun getWeekDayNames(): ArrayList<String> {

        return ArrayList<String>().apply {
            add("Mon")
            add("Tue")
            add("Wed")
            add("Thu")
            add("Fri")
            add("Sat")
        }

    }
}