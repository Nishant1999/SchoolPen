package com.asmanmirza.schoolpen.UI.Teacher.grading.tabs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R

data class HomeWorkDataModel(
    var subjectName:String,
    var sentDate: String,
    var chapterName: String,
    var task: String,
    var gradedStudents: String,
    var newSubmissions:String
)

class HomeWorkAdapter(private val context: Context, private val data: List<HomeWorkDataModel>, private val listner: GradingHomeWorkClickEventHandler): RecyclerView.Adapter<HomeWorkAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var subjectName: TextView = itemView.findViewById(R.id.subject_name)
        var workDate: TextView = itemView.findViewById(R.id.work_date)
        var chapterName: TextView = itemView.findViewById(R.id.chapter_name)
        var assignmentTask: TextView = itemView.findViewById(R.id.assignment_task)
        var gradingNeeded: TextView = itemView.findViewById(R.id.grading_needed)
        var newSubmissions: TextView = itemView.findViewById(R.id.submission_count)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val pos: Int = adapterPosition
            if(pos != RecyclerView.NO_POSITION)
                listner.onClick(pos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teacher_homework,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        holder.subjectName.text = item.subjectName
        holder.workDate.text = item.sentDate
        holder.chapterName.text = item.chapterName
        holder.assignmentTask.text = item.task
        holder.gradingNeeded.text = item.gradedStudents
        holder.newSubmissions.text = item.newSubmissions
    }

    override fun getItemCount(): Int {
        return data.size
    }
}


interface GradingHomeWorkClickEventHandler{
    fun onClick(position: Int)
}