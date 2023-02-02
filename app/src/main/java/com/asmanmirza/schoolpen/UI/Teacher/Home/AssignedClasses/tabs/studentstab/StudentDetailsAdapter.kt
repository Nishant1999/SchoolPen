package com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.studentstab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*


class StudentDetailsDataModel(
    var studentName: String,
    var fatherName: String,
    var age: Int,
    var gender: String,
    var details: String,
    @DrawableRes var studentIcon: Int
)

class StudentDetailsGridAdapter(
    var context: Context,
    var data: ArrayList<StudentDetailsDataModel>
) : RecyclerView.Adapter<StudentDetailsGridAdapter.ViewHolder>() {
    class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var studentName: TextView = itemView.findViewById(R.id.students_name)
        var details: TextView = itemView.findViewById(R.id.students_details)
        var fatherName: TextView = itemView.findViewById(R.id.fathers_name)
        var age: TextView = itemView.findViewById(R.id.age)
        var gender: TextView = itemView.findViewById(R.id.gender)
        var studentImage: CircleImageView = itemView.findViewById(R.id.dpImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_assigned_class_student_details_grid, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.studentName.text = item.studentName
        holder.details.text = item.details
        holder.fatherName.text = item.fatherName
        holder.age.text = item.age.toString()
        holder.gender.text = item.gender
        Glide.with(context).load("https://api.lorem.space/image/face?w=16$position&h=16$position").thumbnail(0.5f).into(holder.studentImage);
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class StudentDetailsListAdapter(
    var context: Context,
    var data: ArrayList<StudentDetailsDataModel>
) : RecyclerView.Adapter<StudentDetailsListAdapter.ViewHolder>() {
    class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var studentName: TextView = itemView.findViewById(R.id.name)
        var details: TextView = itemView.findViewById(R.id.details)
        var studentImage: CircleImageView = itemView.findViewById(R.id.student_details_dp_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_assigned_class_student_details_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        holder.studentName.text = item.studentName
        holder.details.text = item.details
        Glide.with(context).load("https://api.lorem.space/image/face?w=16$position&h=16$position").thumbnail(0.5f).into(holder.studentImage);
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
