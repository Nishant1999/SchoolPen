package com.asmanmirza.schoolpen.UI.Student.assignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityAssignmentHomeBinding

class AssignmentHomeAct : AppCompatActivity(), View.OnClickListener{

    lateinit var binding: ActivityAssignmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAssignmentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtStartTestAssignmentHomeAct.setOnClickListener(this)
    }

    companion object{

        fun startActivity(context: Context){

            var intent = Intent(context, AssignmentHomeAct::class.java)
            context.startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

        when(p0){

            binding.txtStartTestAssignmentHomeAct-> {

                AssignmentQuesAnsAct.startActivity(this)
            }
        }
    }
}