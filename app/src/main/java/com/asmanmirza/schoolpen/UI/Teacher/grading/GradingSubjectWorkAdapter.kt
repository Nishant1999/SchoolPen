package com.asmanmirza.schoolpen.UI.Teacher.grading

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.util.*

data class GradingSubjectWorkDataModel(
    @DrawableRes var studentDp: Int,
    var studentName: String,
    var submitDetails: Boolean = false,
    var grade: String? = null
)

class GradingSubjectWorkAdapter(private val context: Context, private val data: ArrayList<GradingSubjectWorkDataModel>, private val listner: GradingWorkStudentItemClickEventHandler): RecyclerView.Adapter<GradingSubjectWorkAdapter.ViewHolder>() {
   inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val studentName : TextView = itemView.findViewById(R.id.grading_student_name)
        val studentDp: ImageView = itemView.findViewById(R.id.student_details_dp_image)
        val submitDetails: TextView = itemView.findViewById(R.id.received_text)
        val grade: TextView = itemView.findViewById(R.id.student_grade)
        val notGraded: TextView = itemView.findViewById(R.id.student_not_graded)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val pos: Int = adapterPosition
            if(pos != RecyclerView.NO_POSITION)
                listner.onClick(pos.toString())
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_grading_work_each_student,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = data[position]
        holder.studentName.text= item.studentName
        Glide.with(context).load("https://api.lorem.space/image/face?w=16$position&h=15$position").diskCacheStrategy(
            DiskCacheStrategy.ALL).into(holder.studentDp)
       if(item.submitDetails){
           holder.submitDetails.text = "submitted"
       }else{
           holder.submitDetails.text = "not submitted"
       }
        if (item.grade != null) {
            holder.grade.visibility = View.VISIBLE
            holder.notGraded.visibility = View.GONE
            holder.grade.text = item.grade
        }else{
            holder.notGraded.visibility = View.VISIBLE
            holder.grade.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}

interface GradingWorkStudentItemClickEventHandler{
    fun onClick(name: String)
}