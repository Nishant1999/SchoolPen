package com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelMarkedHomeworkTests

class AdapterMarkedHomeWorksTests(var context: Context, var data:ArrayList<ModelMarkedHomeworkTests>):RecyclerView.Adapter<AdapterMarkedHomeWorksTests.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        val title:TextView = itemView.findViewById(R.id.title)
        val submissionDate:TextView = itemView.findViewById(R.id.submitTime)
        val marks:TextView = itemView.findViewById(R.id.marks)
        val teacherName:TextView = itemView.findViewById(R.id.teacherName)
        val teacherStream:TextView = itemView.findViewById(R.id.teachersStream)
        val description:TextView = itemView.findViewById(R.id.description)
        val btnShowHide:ImageButton = itemView.findViewById(R.id.btnOpenHide)
        val layoutHidden:LinearLayout = itemView.findViewById(R.id.layoutHidden)

        @SuppressLint("SetTextI18n")
        fun bind(context: Context, data: ArrayList<ModelMarkedHomeworkTests>, position: Int){

            val md = data[position]
            title.text = md.title
            submissionDate.text = "Submitted on ${md.submitTime}"
            description.text = md.description
            teacherStream.text = md.teacherSubject
            teacherName.text = md.teacherName
            marks.text = "${md.marksGained}/${md.totalMarks}"
            btnShowHide.setOnClickListener {
                if(layoutHidden.visibility == View.VISIBLE){
                    btnShowHide.rotation = 0f
                    layoutHidden.visibility = View.GONE
                }else{
                    btnShowHide.rotation = 180f
                    layoutHidden.visibility = View.VISIBLE
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todays_homework, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, data, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}