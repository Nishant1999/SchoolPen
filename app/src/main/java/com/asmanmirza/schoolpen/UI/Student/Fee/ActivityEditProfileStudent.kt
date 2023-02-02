package com.asmanmirza.schoolpen.UI.Student.Fee

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityEditProfileStudentBinding

class ActivityEditProfileStudent: AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityEditProfileStudentBinding? = null
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditProfileStudentBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.backButton?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.back_button ->{
                finish()
            }
        }
    }

    override fun onBackPressed() {
        finish()
        super.getOnBackPressedDispatcher().onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}