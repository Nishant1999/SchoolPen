package com.asmanmirza.schoolpen.UI.Student.Learn

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.UI.Student.Learn.Adapters.AdapterOptions
import com.asmanmirza.schoolpen.UI.Student.Learn.Adapters.AdapterSelectableDp
import com.asmanmirza.schoolpen.UI.Student.Learn.Models.ModelDp
import com.asmanmirza.schoolpen.databinding.ActivityAddFeedQuestionBinding
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport

class AddFeedQuestionActivity : AppCompatActivity() {

    lateinit var binding:ActivityAddFeedQuestionBinding;
    lateinit var students:ArrayList<ModelDp>;
    lateinit var selectedStudents:ArrayList<ModelDp>;
    lateinit var adapterOptions: AdapterOptions;
    lateinit var options:ArrayList<String>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFeedQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateData(){
        binding.apply {
            getStudents()
            selectedStudents = ArrayList()
            recOptions.layoutManager = LinearLayoutManager(this@AddFeedQuestionActivity, LinearLayoutManager.VERTICAL, false)
            options = ArrayList()
            options.add("Option 1")
            options.add("Option 2")
            adapterOptions = AdapterOptions(this@AddFeedQuestionActivity, options)
            recOptions.adapter = adapterOptions

            btnBack.setOnClickListener {
                finish()
            }

            btnAddOption.setOnClickListener {
                if(options.size + 1  <= 5){
                    options.add("Option ${options.size + 1}")
                    adapterOptions.notifyDataSetChanged()
                    if(options.size == 5){
                        btnAddOption.visibility = View.GONE
                    }
                }

            }
            recClassMem.layoutManager = GridLayoutManager(this@AddFeedQuestionActivity, 5)
            recSelected.layoutManager = GridLayoutManager(this@AddFeedQuestionActivity, 5)

            /*btnSelectAll.setOnClickListener {
                selectedStudents = students
                recSelected.adapter = AdapterSelectableDp(requireContext(), selectedStudents, true)
            }*/

            btnClearAll.setOnClickListener {
                selectedStudents.clear()
                recSelected.adapter = AdapterSelectableDp(this@AddFeedQuestionActivity, selectedStudents, true)
            }

            ItemClickSupport.addTo(recClassMem).setOnItemClickListener { recyclerView, position, v ->
                val md = students[position]
                if(!selectedStudents.contains(md)) {
                    selectedStudents.add(md)
                    recSelected.adapter = AdapterSelectableDp(this@AddFeedQuestionActivity, selectedStudents, true)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun setSelectedSize(size:Int){
        //binding.btnSelectedPeoples.text = "$size people selected"
    }

    fun getStudents(){
        students = ArrayList();
        students.add(ModelDp("0", "https://api.lorem.space/image/face?w=150&h=150", false))
        students.add(ModelDp("1", "https://api.lorem.space/image/face?w=151&h=151", false))
        students.add(ModelDp("2", "https://api.lorem.space/image/face?w=152&h=152", false))
        students.add(ModelDp("3", "https://api.lorem.space/image/face?w=153&h=153", false))
        students.add(ModelDp("4", "https://api.lorem.space/image/face?w=154&h=154", false))
        students.add(ModelDp("5", "https://api.lorem.space/image/face?w=155&h=155", false))
        students.add(ModelDp("6", "https://api.lorem.space/image/face?w=156&h=156", false))
        students.add(ModelDp("7", "https://api.lorem.space/image/face?w=157&h=157", false))
        students.add(ModelDp("8", "https://api.lorem.space/image/face?w=158&h=158", false))
        students.add(ModelDp("9", "https://api.lorem.space/image/face?w=159&h=159", false))

        binding.recClassMem.adapter = AdapterSelectableDp(this@AddFeedQuestionActivity, students, false)
    }

    companion object {
        lateinit var instance: AddFeedQuestionActivity
            private set
    }


}