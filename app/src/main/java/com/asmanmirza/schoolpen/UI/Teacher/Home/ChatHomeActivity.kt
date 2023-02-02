package com.asmanmirza.schoolpen.UI.Teacher.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Models.ModelTeacherChat
import com.asmanmirza.schoolpen.databinding.ActivityChatHomeBinding
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.presentation.main.Teacher.chat.AdapterTeachersChat
import com.google.android.material.tabs.TabLayout

class ChatHomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityChatHomeBinding
    lateinit var adapterTeachersChat: AdapterTeachersChat;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {

            recTeachersChat.layoutManager =
                LinearLayoutManager(this@ChatHomeActivity, LinearLayoutManager.VERTICAL, false)
            adapterTeachersChat = AdapterTeachersChat(this@ChatHomeActivity, getChats());
            recTeachersChat.adapter = adapterTeachersChat

            btnMore.setOnClickListener {
                //findNavController().navigate(R.id.action_teacherFragment_to_timeTableFragment)
            }

            btnBack.setOnClickListener {
                finish()
            }

            tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){

                        0->{
                            adapterTeachersChat = AdapterTeachersChat(this@ChatHomeActivity, getChats())
                            recTeachersChat.adapter = adapterTeachersChat
                        }
                        1->{

                            val data = getChats()
                            val temp = ArrayList<ModelTeacherChat>();
                            for(i in 0 until data.size){
                                val md = data[i]
                                if(!md.isTeacher){
                                    temp.add(md)
                                    adapterTeachersChat = AdapterTeachersChat(this@ChatHomeActivity, temp)
                                    recTeachersChat.adapter = adapterTeachersChat
                                }
                            }
                        }
                        2->{
                            val data = getChats()
                            val temp = ArrayList<ModelTeacherChat>();
                            for(i in 0 until data.size){
                                val md = data[i]
                                if(md.isTeacher){
                                    temp.add(md)
                                    adapterTeachersChat = AdapterTeachersChat(this@ChatHomeActivity, temp)
                                    recTeachersChat.adapter = adapterTeachersChat
                                }
                            }
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

            ItemClickSupport.addTo(recTeachersChat).setOnItemClickListener { recyclerView, position, v ->
                if (position == 0) {
                    startActivity(Intent(this@ChatHomeActivity, TeacherChatDetailActivity::class.java))
                } else {
                    Toast.makeText(
                        this@ChatHomeActivity,
                        "You can only view 1st chat",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

    }
    fun getChats(): ArrayList<ModelTeacherChat> {

        return ArrayList<ModelTeacherChat>().apply {

            add(
                ModelTeacherChat(
                    "",
                    "Barun Iswarary",
                    "Class V",
                    "Meet me during lunch.",
                    0,
                    "23/10/2022",
                    "https://api.lorem.space/image/face?w=150&h=150",
                    isTeacher = false
                )
            )
            add(
                ModelTeacherChat(
                    "",
                    "Jain Edward",
                    "Class V",
                    "Ma'am, can you suggest best maths book from library?",
                    2,
                    "23/10/2022",
                    "https://api.lorem.space/image/face?w=151&h=151",
                    isTeacher = false
                )
            )
            add(
                ModelTeacherChat(
                    "",
                    "Rashmi Agarwal",
                    "Teacher",
                    "Are you up for today's meeting?",
                    1,
                    "23/10/2022",
                    "https://api.lorem.space/image/face?w=152&h=152",
                    isTeacher = true
                )
            )
            add(
                ModelTeacherChat(
                    "",
                    "Sonali Singh",
                    "Teacher",
                    "ok will be there.",
                    0,
                    "15/10/2022",
                    "https://api.lorem.space/image/face?w=153&h=153",
                    isTeacher = true
                )
            )
            add(
                ModelTeacherChat(
                    "",
                    "Nidhi Sharma",
                    "Teacher",
                    "Thank you",
                    0,
                    "12/10/2022",
                    "https://api.lorem.space/image/face?w=154&h=154",
                    isTeacher = true
                )
            )
        }
    }
}