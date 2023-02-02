package com.asmanmirza.schoolpen.UI.Student.assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.asmanmirza.schoolpen.DummyList
import com.asmanmirza.schoolpen.UI.Student.assignment.Adapters.ReviewQuesCountAdapter
import com.asmanmirza.schoolpen.databinding.ActivityAssignmentReviewBinding

class AssignmentReviewActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityAssignmentReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAssignmentReviewBinding.inflate(layoutInflater)

        binding.txtStartTestAssignmentHomeAct.setOnClickListener(this)
        setContentView(binding.root)

        binding.apply {

            headerAssignmentReview.imgBackAssignmentHeader.setOnClickListener(this@AssignmentReviewActivity)
            headerAssignmentReview.txtTimeAssignmentHeader.visibility = View.GONE
            headerAssignmentReview.imgBookmarkAssignmentHeader.visibility = View.GONE
            headerAssignmentReview.rvQuesNoAssignmentHeader.visibility = View.GONE
            headerAssignmentReview.txtQuestionAssignmentHeader.visibility = View.GONE

        }

        setAdapterData()
    }

    fun setAdapterData(){

        binding.apply {

            var adapter = ReviewQuesCountAdapter(this@AssignmentReviewActivity, DummyList.getQuesNo())
            rvQuesNoAssignmentReviewAct.adapter = adapter

        }
    }

    companion object{

        fun startActivity(context: Context){

            var intent = Intent(context, AssignmentReviewActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

        when(p0){

            binding.txtStartTestAssignmentHomeAct -> {

                AssignmentSuccessActivity.startActivity(this)
            }
        }
    }

}