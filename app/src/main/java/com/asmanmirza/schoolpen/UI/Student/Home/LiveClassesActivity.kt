package com.asmanmirza.schoolpen.UI.Student.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Adapters.AdapterLiveClasses
import com.asmanmirza.schoolpen.databinding.ActivityLiveClassesBinding
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.Models.*
import com.asmanmirza.schoolpen.SchoolApp
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelHome
import com.asmanmirza.schoolpen.UI.Student.models.HomeViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.repository.HomeRepository
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import javax.inject.Inject

class LiveClassesActivity : AppCompatActivity() {

    lateinit var binding: ActivityLiveClassesBinding;
    lateinit var myApi: MyApi
    lateinit var data: ArrayList<TodayliveClassDtos>
    private lateinit var homeViewModel: ViewModelHome
    lateinit var db: TinyDB;

    lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveClassesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModelFactory= HomeViewModelFactory(HomeRepository(application))

        homeViewModel = ViewModelProvider(this@LiveClassesActivity,homeViewModelFactory)[ViewModelHome::class.java]

        db = TinyDB(this@LiveClassesActivity)
        val token = db.getString("token")
        homeViewModel.getTodayLiveData(
            "Bearer $token"
        )
        data=ArrayList()
        updateData()
    }

    fun updateData() {

        binding.apply {


            recLiveClasses.layoutManager =
                LinearLayoutManager(this@LiveClassesActivity, LinearLayoutManager.VERTICAL, false);

            homeViewModel.todayData.observe(this@LiveClassesActivity) {
                data.addAll(it)

                binding.recLiveClasses.adapter =
                    AdapterLiveClasses(this@LiveClassesActivity, data)
            }

            btnBack.setOnClickListener {
                finish()
            }
            ItemClickSupport.addTo(recLiveClasses)
                .setOnItemClickListener { recyclerView, position, v ->

                    if (position == 0) {
                        startActivity(
                            Intent(
                                this@LiveClassesActivity,
                                LiveClassDetailActivity::class.java
                            )
                        )
                    } else {
                        Toast.makeText(
                            this@LiveClassesActivity,
                            "You are not authorized to view all the live classes",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    fun getLiveClasses() {
/*
               return ArrayList<ModelLiveClasses>().apply{
    add(ModelLiveClasses("", "History of India", "Sonu Sharma", "Social Science", "21", ""))
    add(ModelLiveClasses("", "Algebraic Expressions", "Nani Mathur", "Mathematics", "45", ""))
    add(ModelLiveClasses("", "Chemical Names", "D Jain", "Science", "32", ""))
    add(ModelLiveClasses("", "Q&A Session", "S Solanki", "English", "16", ""))
}*/
    }

}
