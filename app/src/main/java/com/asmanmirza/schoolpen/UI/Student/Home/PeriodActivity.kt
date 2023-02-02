package com.asmanmirza.schoolpen.UI.Student.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityPeriodBinding
import com.asmanmirza.schoolpen.presentation.main.periods.studentperiods.periodsadapter.AssignmentDataModel
import com.asmanmirza.schoolpen.presentation.main.periods.studentperiods.periodsadapter.StudentAssignmentAdapter
import java.util.ArrayList

class PeriodActivity : AppCompatActivity() {

    lateinit var binding:ActivityPeriodBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeriodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }
    fun updateData(){

        binding.apply {

            readingContentPdf.layoutManager = LinearLayoutManager(this@PeriodActivity,
                LinearLayoutManager.VERTICAL, false)
            readingContentPdf.adapter = StudentAssignmentAdapter(this@PeriodActivity,getAssignmentData())
            exerciseContentPdf.layoutManager = LinearLayoutManager(this@PeriodActivity,
                LinearLayoutManager.VERTICAL, false)
            exerciseContentPdf.adapter = StudentAssignmentAdapter(this@PeriodActivity,getExerciseData())
            backButton.setOnClickListener {
                finish()
            }

        }
    }

    private fun getAssignmentData(): ArrayList<AssignmentDataModel> {
        var list = ArrayList<AssignmentDataModel>()
        list.add(AssignmentDataModel(title="Shapes In Nature", subTitle = "5 min read",icon = R.drawable.ic_pdf_icon))
        list.add(AssignmentDataModel(title="Solids and voids: The shapes", subTitle = "10 min read",icon = R.drawable.ic_pdf_icon))
        list.add(AssignmentDataModel(title="Basic Geometric Shapes", subTitle = "2 min read",icon = R.drawable.ic_pdf_icon))
        return list
    }


    private fun getExerciseData(): ArrayList<AssignmentDataModel> {
        var list = ArrayList<AssignmentDataModel>()
        list.add(AssignmentDataModel(title="Questionnaire 1", subTitle = "5 Questions | 10 minutes",icon= R.drawable.ic_exercise_icon))
        list.add(AssignmentDataModel(title="Questionnaire 2", subTitle = "20 Questions | 30 minutes",icon= R.drawable.ic_exercise_icon))
        list.add(AssignmentDataModel(title="Questionnaire 3", subTitle = "3 Questions | 10 minutes",icon= R.drawable.ic_exercise_icon))
        return list
    }

}