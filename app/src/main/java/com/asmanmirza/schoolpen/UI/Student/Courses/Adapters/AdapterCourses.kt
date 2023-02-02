package com.asmanmirza.schoolpen.UI.Student.Courses.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.ModelCourses
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class AdapterCourses (var context: Context, var data:ArrayList<ModelCourses>) : RecyclerView.Adapter<AdapterCourses.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title:TextView = itemView.findViewById(R.id.title);
        val rating:TextView = itemView.findViewById(R.id.rating);
        val image:ImageView = itemView.findViewById(R.id.courseImage)
        val price:TextView = itemView.findViewById(R.id.price);
        val skills:TextView = itemView.findViewById(R.id.skills);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_courses, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val md = data[position]
        holder.title.text = md.title
        holder.skills.text = Html.fromHtml("<strong style=\"color:#383838\">Skills you'll gain:</strong> ${md.skills}")
        holder.price.text = "INR ${md.price}"
        holder.rating.text = md.rating
        Glide.with(context).load(md.bannerUrl).thumbnail(0.6f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image)
    }

    override fun getItemCount(): Int {
        return 5
    }
}
