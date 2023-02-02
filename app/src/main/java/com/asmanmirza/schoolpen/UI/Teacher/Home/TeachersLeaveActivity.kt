package com.asmanmirza.schoolpen.UI.Teacher.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityTeachersLeaveBinding

class TeachersLeaveActivity : AppCompatActivity() {
    lateinit var binding: ActivityTeachersLeaveBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersLeaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    fun updateData(){

        binding.apply {

            backButton.setOnClickListener {
                finish()
            }

            sickLeaveMask.setOnClickListener {
                binding.vacationLeaveMask.setImageResource(0)
                binding.sickLeaveMask.setImageResource(R.drawable.img_fader_mask)
            }

            vacationLeaveMask.setOnClickListener {
                binding.vacationLeaveMask.rotation=180.0F
                binding.vacationLeaveMask.setImageResource(R.drawable.img_fader_mask)
                binding.sickLeaveMask.setImageResource(0)
            }

        }

    }

}