package com.asmanmirza.schoolpen.UI.Student.assignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityAssignmentSuccessBinding

class AssignmentSuccessActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityAssignmentSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAssignmentSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            headerAssignmentSuccessAct.imgBackAssignmentHeader.setOnClickListener(this@AssignmentSuccessActivity)
            txtReturnHomeAssignmentSuccessAct.setOnClickListener(this@AssignmentSuccessActivity)
            headerAssignmentSuccessAct.txtTimeAssignmentHeader.visibility = View.GONE
            headerAssignmentSuccessAct.imgBookmarkAssignmentHeader.visibility = View.GONE
            headerAssignmentSuccessAct.rvQuesNoAssignmentHeader.visibility = View.GONE
            headerAssignmentSuccessAct.txtQuestionAssignmentHeader.visibility = View.GONE

        }

    }

    companion object{

        fun startActivity(context: Context){

            var intent = Intent(context, AssignmentSuccessActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

        when(p0){

            binding.headerAssignmentSuccessAct.imgBackAssignmentHeader -> {
                finish()
            }

            binding.txtReturnHomeAssignmentSuccessAct -> {

                ZoomImageAct.startActivity(this)
            }
        }
    }

}