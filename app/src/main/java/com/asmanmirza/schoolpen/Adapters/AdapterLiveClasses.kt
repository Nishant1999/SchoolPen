package com.asmanmirza.schoolpen.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.Models.TodayliveClassDtos
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class AdapterLiveClasses(var context: Context, var data:ArrayList<TodayliveClassDtos>) : RecyclerView.Adapter<AdapterLiveClasses.ViewHolder>() {


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        val imageDp:CircleImageView = itemView.findViewById(R.id.imageDp)
        var title:TextView = itemView.findViewById(R.id.title);
        var des:TextView = itemView.findViewById(R.id.description);
        var studentsJoined:TextView = itemView.findViewById(R.id.totalStudents);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_live_class1, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var md = data[position];
        Log.d("+++++++assignedTeacher", md.assignedTeacher?.name.toString())
        holder.des.text = "${md.assignedTeacher?.name} | ${md.subjectDescription}"
        holder.title.text = md.title;
        holder.studentsJoined.text = "0 Student Joined"//"${"md.totalJoinedStudents"} students joined"
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").thumbnail(0.5f).into(holder.imageDp);
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return data.size
    }
}