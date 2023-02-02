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

data class StudentAttendanceDataModel(
    var studentName: String,
    var details: String,
    var attendancePercentage: String,
    var present: String,
    var absent: String,
    @DrawableRes var studentIcon: Int
)

class StudentAttendanceGridAdapter(var context: Context, var data: ArrayList<StudentAttendanceDataModel>) : RecyclerView.Adapter<StudentAttendanceGridAdapter.ViewHolder>() {
    class ViewHolder(var itemView: View): RecyclerView.ViewHolder(itemView){
        var studentName: TextView = itemView.findViewById(R.id.students_name)
        var details: TextView = itemView.findViewById(R.id.students_details)
        var percentage: TextView = itemView.findViewById(R.id.student_attendance_percentage)
        var present: TextView = itemView.findViewById(R.id.student_attendance_present)
        var absent: TextView = itemView.findViewById(R.id.student_attendance_absent)
        var studentImage : CircleImageView = itemView.findViewById(R.id.dpImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_assigned_class_student_attendance_grid,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        holder.studentName.text = item.studentName
        holder.details.text = item.details
        holder.present.text = item.present
        holder.absent.text = item.absent
        holder.percentage.text = item.attendancePercentage
        Glide.with(context).load("https://api.lorem.space/image/face?w=16$position&h=16$position").thumbnail(0.5f).into(holder.studentImage);
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class StudentAttendanceListListAdapter(var context: Context, var data: ArrayList<StudentAttendanceDataModel>) : RecyclerView.Adapter<StudentAttendanceListListAdapter.ViewHolder>() {
    class ViewHolder(var itemView: View): RecyclerView.ViewHolder(itemView){
        var studentName: TextView = itemView.findViewById(R.id.name)
        var details: TextView = itemView.findViewById(R.id.details)
        var present: TextView = itemView.findViewById(R.id.student_attendance_present)
        var absent: TextView = itemView.findViewById(R.id.student_attendance_absent)
        var studentImage : CircleImageView = itemView.findViewById(R.id.dpImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_assigned_class_student_attendance_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        holder.studentName.text = item.studentName
        holder.details.text = item.details
        holder.present.text = item.present
        holder.absent.text = item.absent
        Glide.with(context).load("https://api.lorem.space/image/face?w=16$position&h=16$position").thumbnail(0.5f).into(holder.studentImage);
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
