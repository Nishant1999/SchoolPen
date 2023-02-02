package com.asmanmirza.schoolpen.UI.Student.Performance

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R

class AdapterSubjectWiseMarks(var context: Context, var data:ArrayList<ModelSubjectMarks>) : RecyclerView.Adapter<AdapterSubjectWiseMarks.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val subjectName:TextView = itemView.findViewById(R.id.subjectName);
        val ut1:TextView = itemView.findViewById(R.id.ut1);
        val mt:TextView = itemView.findViewById(R.id.mt);
        val ut2:TextView = itemView.findViewById(R.id.ut2);
        val ft:TextView = itemView.findViewById(R.id.ft);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sub_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val md = data[position];
        holder.subjectName.text = md.subject;
        holder.ut1.text = md.UT1
        holder.mt.text = md.MT
        holder.ft.text = md.FT
        holder.ut2.text = md.UT2
    }

    override fun getItemCount(): Int {
        return data.size
    }


}