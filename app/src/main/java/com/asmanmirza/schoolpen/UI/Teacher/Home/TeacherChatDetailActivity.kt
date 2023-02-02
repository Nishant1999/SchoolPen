package com.asmanmirza.schoolpen.UI.Teacher.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.databinding.ActivityTeacherChatDetailBinding
import com.asmanmirza.schoolpen.presentation.main.Teacher.chat.AdapterChatPToP
import com.asmanmirza.schoolpen.Models.ModelPToP
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class TeacherChatDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityTeacherChatDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {

            Glide.with(this@TeacherChatDetailActivity).load("https://api.lorem.space/image/face?w=150&h=150").thumbnail(0.5f).diskCacheStrategy(
                DiskCacheStrategy.ALL).into(dpImage)

            recChatPToP.layoutManager = LinearLayoutManager(this@TeacherChatDetailActivity, LinearLayoutManager.VERTICAL, false)
            recChatPToP.adapter = AdapterChatPToP(this@TeacherChatDetailActivity, getDummyChats());

            btnBack.setOnClickListener {
                finish()
            }


        }

    }
    fun getDummyChats():ArrayList<ModelPToP>{
        return ArrayList<ModelPToP>().apply {
            add(ModelPToP("1", "Good afternoon sir, I am having some issue with the pythagoras theorem", "", "1 hours ago" ))
            add(ModelPToP("1", "I saw some examples but didn't even understand", "", "1 hours ago" ))
            add(ModelPToP("1", "Can you please send the solved example of this?", "", "1 hours ago" ))
            add(ModelPToP("0", "", "https://i.ytimg.com/vi/VJWzXLl8JX8/maxresdefault.jpg", "1 hours ago" ))
            add(ModelPToP("0", "Check this and try to solve the second program 🙂", "", "1 hours ago" ))
            add(ModelPToP("1", "Can you clear this in more detailed way?", "", "30 min ago" ))
            add(ModelPToP("0", "Ok let's see this", "", "15 min ago" ))
            add(ModelPToP("0", "Meet me during lunch.", "", "15 min ago" ))
        }

    }

}