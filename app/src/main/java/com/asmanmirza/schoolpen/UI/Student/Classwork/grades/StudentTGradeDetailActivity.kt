package com.asmanmirza.schoolpen.UI.Student.Classwork.grades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters.AdapterMarkedHomeWorksTests
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelMarkedHomeworkTests
import com.asmanmirza.schoolpen.databinding.ActivityStudentTgradeDetailBinding



class StudentTGradeDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityStudentTgradeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentTgradeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    fun updateData() {

        binding.apply {

            recTodaysHomework.layoutManager = LinearLayoutManager(
                this@StudentTGradeDetailActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            recTodaysTest.layoutManager = LinearLayoutManager(
                this@StudentTGradeDetailActivity,
                LinearLayoutManager.VERTICAL,
                false
            )

            recTodaysHomework.adapter =
                AdapterMarkedHomeWorksTests(this@StudentTGradeDetailActivity, getHomeWorks())
            recTodaysTest.adapter =
                AdapterMarkedHomeWorksTests(this@StudentTGradeDetailActivity, getTests())

            ItemClickSupport.addTo(recTodaysHomework).setOnItemClickListener(object:OnItemClickListener{
                override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
                    startActivity(Intent(this@StudentTGradeDetailActivity, SubjectTPerformanceStatusActivity::class.java))
                }
            })

            ItemClickSupport.addTo(recTodaysTest).setOnItemClickListener(object:OnItemClickListener{
                override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
                    startActivity(Intent(this@StudentTGradeDetailActivity, SubjectTPerformanceStatusActivity::class.java))
                }
            })

        }

    }

    fun getHomeWorks(): ArrayList<ModelMarkedHomeworkTests> {
        return ArrayList<ModelMarkedHomeworkTests>().apply {
            add(
                ModelMarkedHomeworkTests(
                    "",
                    "Digistive Systems",
                    "24/01/2023",
                    "Kiara Edward",
                    "Science",
                    "",
                    "Create the periodic table and write full names",
                    10,
                    10
                )
            )
            add(
                ModelMarkedHomeworkTests(
                    "",
                    "Permutation and Combination",
                    "23/01/2023",
                    "Sonu Sharma",
                    "Mathematics",
                    "",
                    "Solve 10 questions out of 15 in permutation and combination's questions.",
                    8,
                    10
                )
            )
        }
    }

    fun getTests(): ArrayList<ModelMarkedHomeworkTests> {
        return ArrayList<ModelMarkedHomeworkTests>().apply {
            add(
                ModelMarkedHomeworkTests(
                    "",
                    "Digistive Systems",
                    "24/01/2023",
                    "Kiara Edward",
                    "Science",
                    "",
                    "Create the periodic table and write full names",
                    10,
                    10
                )
            )
            add(
                ModelMarkedHomeworkTests(
                    "",
                    "Permutation and Combination",
                    "23/01/2023",
                    "Sonu Sharma",
                    "Mathematics",
                    "",
                    "Solve 10 questions out of 15 in permutation and combination's questions.",
                    8,
                    10
                )
            )
        }
    }

}