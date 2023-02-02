package com.asmanmirza.schoolpen.UI.Common.Register

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.asmanmirza.schoolpen.Adapters.AdapterRoleSelect
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityRoleSelectorBinding
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.google.android.flexbox.*

class RoleSelectorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoleSelectorBinding;
    lateinit var adapterRoleSelect:AdapterRoleSelect;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoleSelectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateData(){

        binding.apply {

            btnBack.setOnClickListener {
                finish()
            }

            val layoutManager = FlexboxLayoutManager(this@RoleSelectorActivity)
            layoutManager.flexWrap = FlexWrap.WRAP
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.justifyContent = JustifyContent.FLEX_START
            layoutManager.alignItems = AlignItems.FLEX_START
            recRoles.layoutManager = layoutManager

            adapterRoleSelect = AdapterRoleSelect(this@RoleSelectorActivity, ArrayList<String>().apply {
                add("Teacher")
                add("Student")
                add("Parent")
            } )

            recRoles.adapter = adapterRoleSelect;

            val anim: Animation = AnimationUtils.loadAnimation(this@RoleSelectorActivity, R.anim.left_rise)
            title.startAnimation(anim)
            recRoles.startAnimation(anim)

            ItemClickSupport.addTo(recRoles).setOnItemClickListener { recyclerView, position, v ->
                adapterRoleSelect.selected = "$position"
                adapterRoleSelect.notifyDataSetChanged()
                btnNext.visibility = View.VISIBLE
            }


            btnNext.setOnClickListener {
                if(adapterRoleSelect.selected.isEmpty()){
                    Toast.makeText(this@RoleSelectorActivity, "Please select a role", Toast.LENGTH_SHORT).show()
                }else if(adapterRoleSelect.selected.toInt() == 0){
                    startActivity(Intent(this@RoleSelectorActivity, SignupActivity::class.java).putExtra("id", "1"))
                }else if(adapterRoleSelect.selected.toInt() == 1){
                    startActivity(Intent(this@RoleSelectorActivity, SignupActivity::class.java).putExtra("id", "2"))
                }else if(adapterRoleSelect.selected.toInt() == 2){
                    startActivity(Intent(this@RoleSelectorActivity, SignupActivity::class.java).putExtra("id", "4"))
                }
            }

        }

    }

}