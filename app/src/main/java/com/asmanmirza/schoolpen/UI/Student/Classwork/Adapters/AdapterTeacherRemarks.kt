package com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelTeacherRemarks
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class AdapterTeacherRemarks(var context: Context, var data:ArrayList<ModelTeacherRemarks>) : RecyclerView.Adapter<AdapterTeacherRemarks.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val dp:ImageView = itemView.findViewById(R.id.dpImage);
        val name:TextView = itemView.findViewById(R.id.name);
        val timeStamp:TextView = itemView.findViewById(R.id.timestamp);
        val subject:TextView = itemView.findViewById(R.id.subject);
        val message:TextView = itemView.findViewById(R.id.message);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teachers_remarks, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val md = data[position]
        holder.message.text = md.message
        holder.timeStamp.text = md.timeStamp
        holder.subject.text = md.subject
        holder.name.text = md.name
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").thumbnail(0.7f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.dp)

    }

    override fun getItemCount(): Int {
        return data.size
    }

}