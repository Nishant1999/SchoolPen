package com.asmanmirza.schoolpen.UI.Teacher.grading

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import java.util.*

data class StudentsAnswerDataModel(
    var question: String,
    var time: String,
    var maxMarks: String,
    var longAnwer: String? = null,
    var isObjective: Boolean = false,
    var isNotCorrect: Boolean = false,
    var selectedOption: String? = null,
    var correctOption: String? = null
)


class StudentsAnswerAdapter(
    private val context: Context,
    private val data: ArrayList<StudentsAnswerDataModel>
) : RecyclerView.Adapter<StudentsAnswerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question: TextView = itemView.findViewById(R.id.assignment_question)
        val timeText: TextView = itemView.findViewById(R.id.text_time)
        val marks: TextView = itemView.findViewById(R.id.text_max_marks)
        val longAnswer: TextView = itemView.findViewById(R.id.assignment_long_answer)
        val selectedAnsHeading: TextView = itemView.findViewById(R.id.selected_answer_heading)
        val selectedAns: TextView = itemView.findViewById(R.id.selected_answer)
        val correctAnsHeading: TextView = itemView.findViewById(R.id.correct_answer_heading)
        val correctAns: TextView = itemView.findViewById(R.id.correct_answer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_student_assignment_answer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        if (item.isObjective) {
            holder.correctAnsHeading.visibility = View.VISIBLE
            holder.correctAns.visibility = View.VISIBLE
            holder.correctAns.text = item.correctOption

            if (item.isNotCorrect) {
                holder.selectedAnsHeading.visibility = View.VISIBLE
                holder.selectedAns.visibility = View.VISIBLE
                holder.selectedAns.text = item.selectedOption
            }
            holder.longAnswer.visibility = View.GONE
        } else {
            holder.correctAnsHeading.visibility = View.GONE
            holder.correctAns.visibility = View.GONE
            holder.selectedAnsHeading.visibility = View.GONE
            holder.selectedAns.visibility = View.GONE
            holder.longAnswer.visibility = View.VISIBLE
            holder.longAnswer.text = item.longAnwer

        }

        holder.marks.text = item.maxMarks
        holder.question.text = item.question
        holder.timeText.text = item.time
    }

    override fun getItemCount(): Int {
       return data.size
    }
}