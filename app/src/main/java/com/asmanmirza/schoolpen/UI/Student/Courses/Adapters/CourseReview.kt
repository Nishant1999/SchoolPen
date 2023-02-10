package com.asmanmirza.schoolpen.UI.Student.Courses.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseReviewDTO


class CourseReview(var context: Context, var data:ArrayList<CourseReviewDTO>) : RecyclerView.Adapter<CourseReview.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        //val imageDp: CircleImageView = itemView.findViewById(R.id.imageDp)
        var name: TextView = itemView.findViewById(R.id.review_student_name);
        var des: TextView = itemView.findViewById(R.id.review)
        var date:TextView=itemView.findViewById(R.id.review_date)
        var className:TextView=itemView.findViewById(R.id.review_student_class)
        val ratingBar:RatingBar=itemView.findViewById(R.id.ratingBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.review_details_course, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val md = data[position];

        holder.name.text=md.student.name
        holder.des.text=md.review
        holder.className.text=md.student.classes.className


        val progress: Drawable = holder.ratingBar.progressDrawable
        holder.ratingBar.stepSize=0.5f
        holder.ratingBar.rating= md.feedBackStar.toFloat()

        DrawableCompat.setTint(progress,ContextCompat.getColor(context,R.color.stars_color))


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