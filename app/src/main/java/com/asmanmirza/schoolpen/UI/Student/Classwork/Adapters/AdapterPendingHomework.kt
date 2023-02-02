package com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelPendingWork

class AdapterPendingHomework(var context: Context, var data:ArrayList<ModelPendingWork>) : RecyclerView.Adapter<AdapterPendingHomework.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val title: TextView = itemView.findViewById(R.id.title);
        val subject: TextView = itemView.findViewById(R.id.subject)
        val des: TextView = itemView.findViewById(R.id.des);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pending_homework, parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position == 1){
            holder.date.text = "Due on 31/10/2022"
            holder.title.text = "Chemical Names"
            holder.des.text = "Create the periodic table and write full names"
            holder.subject.text = "Science"
        }else{
            holder.date.text = "Due on 30/10/2022"
            holder.title.text = "Digistive system"
            holder.des.text = "Write the parts of the digestive system expalining thr functions"
            holder.subject.text = "Science"
        }

    }

    override fun getItemCount(): Int {
        return 2
    }

}