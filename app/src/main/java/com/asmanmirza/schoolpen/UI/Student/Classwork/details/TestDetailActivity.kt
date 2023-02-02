package com.asmanmirza.schoolpen.UI.Student.Classwork.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.databinding.ActivityTestDetailBinding

class TestDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityTestDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestDetailBinding.inflate(layoutInflater);
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }

    }
}