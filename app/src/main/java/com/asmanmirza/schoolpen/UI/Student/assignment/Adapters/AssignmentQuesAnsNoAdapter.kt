package com.asmanmirza.schoolpen.UI.Student.assignment.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.assignment.Models.AssignmentQuesAnsModel

class AssignmentQuesAnsNoAdapter (var context: Context, var data:ArrayList<AssignmentQuesAnsModel>,val  action: (Int) -> Unit) : RecyclerView.Adapter<AssignmentQuesAnsNoAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtNo_assignmnt_ques_no_header_row:TextView = itemView.findViewById(R.id.txtNo_assignmnt_ques_no_header_row)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.assignment_ques_number_header_row, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = data[position]

        holder.txtNo_assignmnt_ques_no_header_row.text = (position + 1).toString()

        if(dataModel.isSelected){
            holder.txtNo_assignmnt_ques_no_header_row.background = ContextCompat.getDrawable(context, R.drawable.circle_back)
            holder.txtNo_assignmnt_ques_no_header_row.setTextColor(context.resources.getColor(R.color.homework_detail_tint))
            holder.txtNo_assignmnt_ques_no_header_row.alpha = 1.0f

        } else {
            holder.txtNo_assignmnt_ques_no_header_row.setBackgroundResource(0)
            holder.txtNo_assignmnt_ques_no_header_row.setTextColor(context.resources.getColor(R.color.white))
            holder.txtNo_assignmnt_ques_no_header_row.alpha = 0.7f
        }

        holder.txtNo_assignmnt_ques_no_header_row.setOnClickListener {
            action(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
