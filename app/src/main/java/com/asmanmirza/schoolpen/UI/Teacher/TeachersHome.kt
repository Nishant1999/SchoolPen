package com.asmanmirza.schoolpen.UI.Teacher

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.asmanmirza.schoolpen.Adapters.PageAdapterTeachers
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityTeachersHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class TeachersHome : AppCompatActivity() {

    private lateinit var binding:ActivityTeachersHomeBinding;
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instance = this
        updateData()
    }
    private fun updateData(){

        binding.apply {
            viewPager.adapter = PageAdapterTeachers(supportFragmentManager, bottomNavBar.tabCount)
            viewPager.setSwipePagingEnabled(false)
            bottomNavBar.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                    when(tab?.position){
                        0->{
                            viewPager.currentItem = 0
                        }
                        1->{
                            viewPager.currentItem = 1
                        }
                        2->{
                            viewPager.currentItem = 2
                        }
                        3->{
                            viewPager.currentItem = 3
                        }
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.icon?.setColorFilter(Color.parseColor("#d1d1d1"), PorterDuff.Mode.SRC_IN)
                }
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })

        }

    }

    fun setViewPagerPosition(pos:Int){

        if(pos in 0..3){

            binding.viewPager.currentItem = pos

        }

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if(binding.viewPager.currentItem != 0){
            binding.viewPager.currentItem = 0
        }else{
            if(doubleBackToExitPressedOnce){
                super.onBackPressed()
                return
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        }


    }


    companion object{

        lateinit var instance:TeachersHome
        private set

    }

}