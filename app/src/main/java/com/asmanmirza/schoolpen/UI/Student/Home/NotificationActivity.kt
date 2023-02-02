package com.asmanmirza.schoolpen.UI.Student.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.UI.Student.Home.Adapter.AdapterNotificationStudent
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelStudentNotification
import com.asmanmirza.schoolpen.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    lateinit var binding:ActivityNotificationBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    fun updateData(){

        binding.apply {
            recNotifications.layoutManager = LinearLayoutManager(this@NotificationActivity, LinearLayoutManager.VERTICAL, false)
            recNotifications.adapter = AdapterNotificationStudent(this@NotificationActivity, getNotifications());

            btnBack.setOnClickListener {
                finish()
            }
        }

    }


    fun getNotifications():ArrayList<ModelStudentNotification>{
        return ArrayList<ModelStudentNotification>().apply {
            add(ModelStudentNotification("", "Fees For Month of Feb", "Fee Payment", "Submit fees for the month of February by 15, after that late fees will be applied", "45 mins ago", 0))
            add(ModelStudentNotification("", "Mathematics Assignment", "Class Assignment", "Quick permutation and combination assignment which was discussed in today's classes", "2 hr ago", 1))
            add(ModelStudentNotification("", "English Mid Term II", "Session Exam", "Your exam has been schedule for 1st of February. Please carry all the required instruments.", "10 hr ago", 2))

        }

    }

}