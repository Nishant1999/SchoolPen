package com.asmanmirza.schoolpen.UI.Common.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.asmanmirza.schoolpen.UI.Student.StudentHome
import com.asmanmirza.schoolpen.databinding.ActivitySplashBinding
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.UI.Parent.ParentHomeActivity
import com.asmanmirza.schoolpen.UI.Teacher.TeachersHome
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateView()

    }

    fun updateView(){

        binding.apply {
            lifecycleScope.launchWhenResumed {
                delay(3_000)
                val db = TinyDB(this@SplashActivity)
                if(db.getString("token").isNullOrEmpty() || db.getString("userId").isNullOrEmpty() || db.getString("authority").isNullOrEmpty()) {
                    startActivity(Intent(this@SplashActivity, StartActivity::class.java))
                }else{
                    if(db.getString("authority") == "1") {
                        startActivity(Intent(this@SplashActivity, TeachersHome::class.java))
                    }else if(db.getString("authority") == "2"){
                        startActivity(Intent(this@SplashActivity, StudentHome::class.java))
                    }else if(db.getString("authority") == "4"){
                        startActivity(Intent(this@SplashActivity, ParentHomeActivity::class.java))
                    }
                }
                finish()
            }

        }

    }

}