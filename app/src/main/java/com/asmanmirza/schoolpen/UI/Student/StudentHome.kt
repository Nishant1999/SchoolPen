package com.asmanmirza.schoolpen.UI.Student

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.asmanmirza.schoolpen.Adapters.PageAdapterStudents
import com.asmanmirza.schoolpen.BaseActivity
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityStudentHomeBinding
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentHome : BaseActivity(), View.OnClickListener {

    lateinit var binding:ActivityStudentHomeBinding
    private val scaleFactor = 5f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()

    }

    @SuppressLint("ClickableViewAccessibility")
    fun updateData(){

        binding.apply {

            binding.ivMenu.setOnClickListener(this@StudentHome)
            binding.drawerMenu.tvTeachersStudentHomeAct.setOnClickListener(this@StudentHome)
            binding.drawerMenu.tvClassmateStudentHomeAct.setOnClickListener(this@StudentHome)
            binding.drawerMenu.tvSyllabusStudentHomeAct.setOnClickListener(this@StudentHome)
            binding.drawerMenu.tvAttendanceStudentHomeAct.setOnClickListener(this@StudentHome)
            binding.drawerMenu.tvCalendarStudentHomeAct.setOnClickListener(this@StudentHome)
            binding.drawerMenu.tvSchoolBoardStudentHomeAct.setOnClickListener(this@StudentHome)
            binding.drawerMenu.tvFeePortalStudentHomeAct.setOnClickListener(this@StudentHome)
            binding.drawerMenu.btnExitStudentHomeAct.setOnClickListener(this@StudentHome)

            viewPager.adapter = PageAdapterStudents(supportFragmentManager, bottomNavBar.tabCount)
            viewPager.setSwipePagingEnabled(false)
            viewPager.currentItem = 0
            viewPager.beginFakeDrag()
            bottomNavBar.getTabAt(0)?.icon?.setColorFilter(Color.parseColor("#9163D7"), PorterDuff.Mode.SRC_IN)
            bottomNavBar.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.icon?.setColorFilter(Color.parseColor("#9163D7"), PorterDuff.Mode.SRC_IN)
                    when(tab?.position){
                        0->{
                            viewPager.currentItem = 0
//                            hideTopButtons(false)
                        }
                        1->{
                            viewPager.currentItem = 1
//                            hideTopButtons(true)
                        }
                        2->{
                            viewPager.currentItem = 2
//                            hideTopButtons(true)
                        }
                        3->{
                            viewPager.currentItem = 3
//                            hideTopButtons(true)
                        }
                        4->{
                            viewPager.currentItem = 4
//                            hideTopButtons(false)
                        }
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.icon?.setColorFilter(Color.parseColor("#707070"), PorterDuff.Mode.SRC_IN)
                }
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }
        showDrawer()
    }

    fun updateStatusBarColor(color: String?) { // Color must be in hexadecimal fromat
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor(color)
    }

    /*fun hideTopButtons(hide:Boolean){
        if(hide) {
            binding.ivMenu.visibility = View.GONE
        }else{
            binding.ivMenu.visibility = View.VISIBLE
        }

    }*/

    private fun showDrawer() {

        binding.drawerLayout.setScrimColor(Color.TRANSPARENT)

        val actionBarDrawerToggle = object : ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close){

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                binding.cvMain.radius = 65f
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                binding.cvMain.radius = 0f
            }


            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX = drawerView.width * slideOffset
                binding.cvMain.translationX = slideX

                binding.cvMain.scaleX = 1 - (slideOffset / scaleFactor)
                binding.cvMain.scaleY = 1 - (slideOffset / scaleFactor)
            }
        }
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)


        /*val popup = PopupWindow(this)
        val layout: View = layoutInflater.inflate(R.layout.item_drawer_layout, null)
        popup.contentView = layout
        // Set content width and height
        // Set content width and height
        popup.height = WindowManager.LayoutParams.MATCH_PARENT
        popup.width = WindowManager.LayoutParams.MATCH_PARENT
        popup.animationStyle = R.style.Animation_drawer

        val parent: LinearLayout = layout.findViewById(R.id.parent_layout);

        parent.setOnClickListener {
            popup.dismiss()
        }


        val btnExit: TextView = layout.findViewById(R.id.btnExit);
        btnExit.setOnClickListener {
            val db = TinyDB(this)
            db.clear()
            Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show()
            finishAffinity()
        }

        popup.isOutsideTouchable = true
        popup.isFocusable = true
        // Show anchored to button
        // Show anchored to button
        popup.setBackgroundDrawable(BitmapDrawable())
        popup.showAtLocation(
            binding.ivMenu, Gravity.BOTTOM, 0,
            0
        )
        popup.dimBehind()*/
    }

    fun PopupWindow.dimBehind() {
        val container = contentView.rootView
        val context = contentView.context
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = container.layoutParams as WindowManager.LayoutParams
        p.flags = p.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        p.dimAmount = 0.3f
        wm.updateViewLayout(container, p)
    }

    fun openDrawer(){

        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onClick(p0: View?) {

        when (p0) {

            binding.ivMenu -> {
                openDrawer()
            }

            binding.drawerMenu.tvTeachersStudentHomeAct -> {
                closeDrawer()
            }
            binding.drawerMenu.tvClassmateStudentHomeAct -> {
                closeDrawer()
            }
            binding.drawerMenu.tvSyllabusStudentHomeAct -> {
                closeDrawer()
            }
            binding.drawerMenu.tvAttendanceStudentHomeAct -> {
                closeDrawer()
            }
            binding.drawerMenu.tvCalendarStudentHomeAct -> {
                closeDrawer()
            }
            binding.drawerMenu.tvSchoolBoardStudentHomeAct -> {
                closeDrawer()
            }
            binding.drawerMenu.tvFeePortalStudentHomeAct -> {
                closeDrawer()
            }
            binding.drawerMenu.btnExitStudentHomeAct -> {
                closeDrawer()
                val db = TinyDB(this)
                db.clear()
                Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show()
                finishAffinity()
            }
        }
    }

    private fun closeDrawer() {
        binding.drawerLayout.close()
    }


}