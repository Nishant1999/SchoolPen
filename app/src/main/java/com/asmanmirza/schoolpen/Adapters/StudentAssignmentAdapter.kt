package com.asmanmirza.schoolpen.presentation.main.periods.studentperiods.periodsadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Periods.ReadingPeriodActivity
import org.w3c.dom.Text
import java.util.*

data class AssignmentDataModel(
    @DrawableRes var icon: Int,
    var title: String,
    var subTitle: String
)

class StudentAssignmentAdapter(private var context: Context, var data: ArrayList<AssignmentDataModel>) :
    RecyclerView.Adapter<StudentAssignmentAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var icon: ImageButton = itemView.findViewById(R.id.imageWinner)
        val title: TextView = itemView.findViewById(R.id.text_assignment_title)
        var subTitle: TextView = itemView.findViewById(R.id.text_assignment_subtitle)
        var instructor_info_container_button: AppCompatButton = itemView.findViewById(R.id.instructor_info_container_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chapter_attachments,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = data[position]
        holder.title.text = item.title
        holder.subTitle.text = item.subTitle
        holder.icon.setImageResource(item.icon)

        holder.instructor_info_container_button.setOnClickListener {
            ReadingPeriodActivity.startActivity(context)
        }
    }

    override fun getItemCount(): Int = data.size
}