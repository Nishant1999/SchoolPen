package com.asmanmirza.schoolpen.UI.Student.Home.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.details.HomeWorkDetailActivity
import com.asmanmirza.schoolpen.UI.Student.Classwork.details.TestDetailActivity
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelStudentNotification
import com.bumptech.glide.Glide

class AdapterNotificationStudent(var context:Context, var data:ArrayList<ModelStudentNotification>):RecyclerView.Adapter<AdapterNotificationStudent.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        val logo:ImageView = itemView.findViewById(R.id.notificationTypeImage)
        val title:TextView = itemView.findViewById(R.id.notificationTitle)
        val des:TextView = itemView.findViewById(R.id.notificationDes)
        val brief:TextView = itemView.findViewById(R.id.notificationBrief)
        val btnAction:TextView = itemView.findViewById(R.id.btnAction)
        val timeStamp:TextView = itemView.findViewById(R.id.timeStamp)

        fun bind(context: Context, data: ArrayList<ModelStudentNotification>, position: Int){

            if(position == 0){
                itemView.background.setColorFilter(Color.parseColor("#F4F0FB"), PorterDuff.Mode.SRC_IN)
            }else{
                itemView.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
            }

            val md = data[position];
            when(md.type){
                0->{
                    Glide.with(context).load(R.drawable.ic_baseline_fee).into(logo)
                    btnAction.text = "Pay Now"
                    btnAction.background.setColorFilter(Color.parseColor("#6200ee"), PorterDuff.Mode.SRC_IN)
                }
                1->{
                    btnAction.text = "View Assignment"
                    btnAction.setTextColor(Color.parseColor("#6200ee"))
                    btnAction.background.setColorFilter(Color.parseColor("#206200ee"), PorterDuff.Mode.SRC_IN)
                    Glide.with(context).load(R.drawable.ic_round_auto_stories_24).into(logo)
                    btnAction.setOnClickListener {
                        context.startActivity(Intent(context, HomeWorkDetailActivity::class.java))
                    }
                }
                2->{
                    btnAction.text = "View Details"
                    btnAction.setTextColor(Color.parseColor("#ffbb33"))
                    btnAction.background.setColorFilter(Color.parseColor("#20ffbb33"), PorterDuff.Mode.SRC_IN)
                    Glide.with(context).load(R.drawable.ic_test_attempted).into(logo)
                    btnAction.setOnClickListener {
                        context.startActivity(Intent(context, TestDetailActivity::class.java))
                    }
                }
            }
            title.text = md.title
            des.text = md.description
            brief.text = md.brief
            timeStamp.text = md.timeStamp


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notification_student, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, data, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}