package com.asmanmirza.schoolpen.UI.Common.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Common.Login.LoginActivity
import com.asmanmirza.schoolpen.UI.Common.Register.RoleSelectorActivity
import com.asmanmirza.schoolpen.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {
            btnLogin.setOnClickListener {
                startActivity(Intent(this@StartActivity, LoginActivity::class.java))
            }
            btnSignup.setOnClickListener {
                startActivity(Intent(this@StartActivity, RoleSelectorActivity::class.java))
            }
        }
    }

}