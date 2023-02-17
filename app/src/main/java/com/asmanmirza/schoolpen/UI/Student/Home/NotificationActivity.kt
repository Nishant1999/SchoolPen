package com.asmanmirza.schoolpen.UI.Student.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Helpers.ApiClient
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.Models.TodayliveClassDtos
import com.asmanmirza.schoolpen.UI.Student.Home.Adapter.AdapterNotificationStudent
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelStudentNotification
import com.asmanmirza.schoolpen.UI.Student.Home.Models.NotificationDTO
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import com.asmanmirza.schoolpen.databinding.ActivityNotificationBinding
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotificationActivity : AppCompatActivity() {

    lateinit var binding:ActivityNotificationBinding
    lateinit var db: TinyDB;
    lateinit var myApi: MyApi
    lateinit var data: ArrayList<NotificationDTO>
    lateinit var notificationData: ArrayList<NotificationDTO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = TinyDB(this)
        updateData()
    }

    fun updateData(){

        binding.apply {
            recNotifications.layoutManager = LinearLayoutManager(this@NotificationActivity, LinearLayoutManager.VERTICAL, false)
            recNotificationsTask.layoutManager = LinearLayoutManager(this@NotificationActivity, LinearLayoutManager.VERTICAL, false)
            recNotificationsExam.layoutManager = LinearLayoutManager(this@NotificationActivity, LinearLayoutManager.VERTICAL, false)
            getAllNotification()


            tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){

                        0->{
                            getAllNotification()
                            showView(layoutAllNotification, layoutTaskNotification, layoutExamNotification)
                        }
                        1->{
                            getTaskNotification()
                            showView(layoutTaskNotification, layoutAllNotification, layoutExamNotification)
                        }
                        2->{
                            getExamNotification()
                            showView(layoutExamNotification, layoutAllNotification, layoutTaskNotification)
                        }

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })

            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun getAllNotification() {
        myApi = ApiClient.getClient()?.create(MyApi::class.java)!!

        myApi.getAllNotification("Bearer"+" "+db.getString("token")).enqueue(object : Callback<ModelStudentNotification>{
            override fun onResponse(
                call: Call<ModelStudentNotification>,
                response: Response<ModelStudentNotification>
            ) {
                if(response.isSuccessful) {
                  //  Log.d("+++student notification", response.body().toString())
                    //recNotifications.adapter = AdapterNotificationStudent(this@NotificationActivity);

                    val d= response.body()?.data

                    val notification= d?.notificationDTOs



                    Log.d("++++notification",notification.toString())
                    data= notification as ArrayList<NotificationDTO>



                }else{
                   Toast.makeText(this@NotificationActivity,"Error",Toast.LENGTH_LONG).show()
                }

                binding.recNotifications.adapter = AdapterNotificationStudent(this@NotificationActivity,data)
//                binding.recNotificationsTask.adapter = AdapterNotificationStudent(this@NotificationActivity,data)
  //              binding.recNotificationsExam.adapter = AdapterNotificationStudent(this@NotificationActivity,data)
            }

            override fun onFailure(call: Call<ModelStudentNotification>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getTaskNotification() {
        notificationData= ArrayList()
        myApi = ApiClient.getClient()?.create(MyApi::class.java)!!

        myApi.getAllNotification("Bearer"+" "+db.getString("token")).enqueue(object : Callback<ModelStudentNotification>{
            override fun onResponse(
                call: Call<ModelStudentNotification>,
                response: Response<ModelStudentNotification>
            ) {
                if(response.isSuccessful) {
                    //  Log.d("+++student notification", response.body().toString())
                    //recNotifications.adapter = AdapterNotificationStudent(this@NotificationActivity);

                    val d= response.body()?.data

                    val notification= d?.notificationDTOs



                    Log.d("++++notification",notification.toString())


                    data= notification as ArrayList<NotificationDTO>

                    for(i in data){
                        if(i.type=="tasks"){
                            notificationData.add(i)
                        }
                    }


                }else{
                    Toast.makeText(this@NotificationActivity,"Error",Toast.LENGTH_LONG).show()
                }

                //binding.recNotifications.adapter = AdapterNotificationStudent(this@NotificationActivity,data)
                binding.recNotificationsTask.adapter = AdapterNotificationStudent(this@NotificationActivity,notificationData)
                //binding.recNotificationsExam.adapter = AdapterNotificationStudent(this@NotificationActivity,data)
            }

            override fun onFailure(call: Call<ModelStudentNotification>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getExamNotification() {
        notificationData= ArrayList()
        myApi = ApiClient.getClient()?.create(MyApi::class.java)!!

        myApi.getAllNotification("Bearer"+" "+db.getString("token")).enqueue(object : Callback<ModelStudentNotification>{
            override fun onResponse(
                call: Call<ModelStudentNotification>,
                response: Response<ModelStudentNotification>
            ) {
                if(response.isSuccessful) {
                    //  Log.d("+++student notification", response.body().toString())
                    //recNotifications.adapter = AdapterNotificationStudent(this@NotificationActivity);

                    val d= response.body()?.data

                    val notification= d?.notificationDTOs



                    Log.d("++++notification",notification.toString())
                    data= notification as ArrayList<NotificationDTO>

                    for(i in data){
                        if(i.type=="exams"){
                            notificationData.add(i)
                        }
                    }

                }else{
                    Toast.makeText(this@NotificationActivity,"Error",Toast.LENGTH_LONG).show()
                }

                //binding.recNotifications.adapter = AdapterNotificationStudent(this@NotificationActivity,data)
                //binding.recNotificationsTask.adapter = AdapterNotificationStudent(this@NotificationActivity,data)
                binding.recNotificationsExam.adapter = AdapterNotificationStudent(this@NotificationActivity,notificationData)
            }

            override fun onFailure(call: Call<ModelStudentNotification>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun showView(v1: View, v2: View, v3: View){
        v1.visibility = View.VISIBLE;
        v2.visibility = View.GONE
        v3.visibility = View.GONE
    }


/*    fun getNotifications():ArrayList<ModelStudentNotification>{
        return ArrayList<ModelStudentNotification>().apply {
            add(ModelStudentNotification("", "Fees For Month of Feb", "Fee Payment", "Submit fees for the month of February by 15, after that late fees will be applied", "45 mins ago", 0))
            add(ModelStudentNotification("", "Mathematics Assignment", "Class Assignment", "Quick permutation and combination assignment which was discussed in today's classes", "2 hr ago", 1))
            add(ModelStudentNotification("", "English Mid Term II", "Session Exam", "Your exam has been schedule for 1st of February. Please carry all the required instruments.", "10 hr ago", 2))

        }

    }*/

}