package com.asmanmirza.schoolpen.UI.Student.assignment.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.assignment.Models.ReviewQuestionModel

class ReviewQuesCountAdapter (var context: Context, var data:ArrayList<ReviewQuestionModel>) : RecyclerView.Adapter<ReviewQuesCountAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtNo_assignmnt_ques_no_row:TextView = itemView.findViewById(R.id.txtNo_assignmnt_ques_no_row)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.assignment_ques_number_row, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reviewModel = data[position]

        holder.txtNo_assignmnt_ques_no_row.text = reviewModel.title

        if(reviewModel.type.equals("Marked for Review", true)){
            holder.txtNo_assignmnt_ques_no_row.setBackgroundResource(R.drawable.light_blue_circle)

        } else if(reviewModel.type.equals("Submit", true)){
            holder.txtNo_assignmnt_ques_no_row.setBackgroundResource(R.drawable.blue_circle)

        } else if(reviewModel.type.equals("Skipped", true)){
            holder.txtNo_assignmnt_ques_no_row.setBackgroundResource(R.drawable.yellow_circle)

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
