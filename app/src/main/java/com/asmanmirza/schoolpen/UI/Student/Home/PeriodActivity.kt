package com.asmanmirza.schoolpen.UI.Student.Home

import android.annotation.SuppressLint
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.Models.Data
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Home.Models.PeriodViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelHome
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelPeriod
import com.asmanmirza.schoolpen.UI.Student.models.HomeViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.repository.HomeRepository
import com.asmanmirza.schoolpen.UI.Student.repository.PeriodRepo
import com.asmanmirza.schoolpen.databinding.ActivityPeriodBinding
import com.asmanmirza.schoolpen.presentation.main.periods.studentperiods.periodsadapter.AssignmentDataModel
import com.asmanmirza.schoolpen.presentation.main.periods.studentperiods.periodsadapter.StudentAssignmentAdapter
import java.util.ArrayList
import javax.inject.Inject

class PeriodActivity : AppCompatActivity() {

    lateinit var binding:ActivityPeriodBinding;
    var id:Int = 0
    lateinit var db: TinyDB;
    lateinit var periodViewModel: ViewModelPeriod


    @Inject
    lateinit var periodViewModelFactory: PeriodViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeriodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id=intent.getIntExtra("id",0)
        Toast.makeText(this,""+id,Toast.LENGTH_LONG).show()

        db= TinyDB(this)
        periodViewModelFactory= PeriodViewModelFactory(PeriodRepo(application))

        periodViewModel =
            ViewModelProvider(this, periodViewModelFactory)[ViewModelPeriod::class.java]
        val token = db.getString("token")

        periodViewModel.getPeriodById(id,"Bearer $token")


        updateData()
    }
    @SuppressLint("SetTextI18n")
    fun updateData(){

        binding.apply {

            periodViewModel.periodDataId.observe(this@PeriodActivity){

                binding.periodName.text=it.periodName
                binding.subjectName.text=it.assignedSubject.subjectName
                binding.teacherName.text=it.assignedTeacher.name
                binding.teacherSubjectClass.text=it.assignedSubject.subjectName+"|"+it.classes.className
            }

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