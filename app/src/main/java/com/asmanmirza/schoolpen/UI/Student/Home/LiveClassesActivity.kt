package com.asmanmirza.schoolpen.UI.Student.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Adapters.AdapterLiveClasses
import com.asmanmirza.schoolpen.Models.ModelLiveClasses
import com.asmanmirza.schoolpen.databinding.ActivityLiveClassesBinding
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport

class LiveClassesActivity : AppCompatActivity() {

    lateinit var binding:ActivityLiveClassesBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveClassesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    fun updateData(){

        binding.apply {

            recLiveClasses.layoutManager = LinearLayoutManager(this@LiveClassesActivity, LinearLayoutManager.VERTICAL, false);
            recLiveClasses.adapter = AdapterLiveClasses(this@LiveClassesActivity, getLiveClasses())

            btnBack.setOnClickListener {
                finish()
            }
            ItemClickSupport.addTo(recLiveClasses).setOnItemClickListener { recyclerView, position, v ->

                if(position == 0) {
                    startActivity(Intent(this@LiveClassesActivity, LiveClassDetailActivity::class.java))
                }else{
                    Toast.makeText(this@LiveClassesActivity, "You are not authorized to view all the live classes", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getLiveClasses():ArrayList<ModelLiveClasses>{
        return ArrayList<ModelLiveClasses>().apply{
            add(ModelLiveClasses("", "History of India", "Sonu Sharma", "Social Science", "21", ""))
            add(ModelLiveClasses("", "Algebraic Expressions", "Nani Mathur", "Mathematics", "45", ""))
            add(ModelLiveClasses("", "Chemical Names", "D Jain", "Science", "32", ""))
            add(ModelLiveClasses("", "Q&A Session", "S Solanki", "English", "16", ""))
        }
    }



}