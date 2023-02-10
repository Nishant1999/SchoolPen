package com.asmanmirza.schoolpen.UI.Student.Courses.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Courses.CourseDetailActivity
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.CourseDTO
import com.asmanmirza.schoolpen.UI.Student.Courses.Models.ModelCourses
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class AdapterCourses (var context: Context, var data:ArrayList<CourseDTO>, var i:Int) : RecyclerView.Adapter<AdapterCourses.ViewHolder>() {

    //private lateinit var mListener:OnItemClickListener



    /*fun setOnItemClickListener(listener: OnItemClickListener){
        mListener=listener
    }*/

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title:TextView = itemView.findViewById(R.id.title);
        val rating:TextView = itemView.findViewById(R.id.rating);
        val image:ImageView = itemView.findViewById(R.id.courseImage)
        val price:TextView = itemView.findViewById(R.id.price);
        val skills:TextView = itemView.findViewById(R.id.skills);
        val btnText:TextView=itemView.findViewById(R.id.txt_btn)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_courses, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(i==1){
            holder.btnText.text = "Resume Now"
        }
        val md = data[position]
        holder.title.text = md.courseName
        holder.skills.text = Html.fromHtml("<strong style=\"color:#383838\">Skills you'll gain:</strong> Radian Measure,Triangle Solution,Amplitude Solving")
        holder.price.text = "INR ${md.courseFee}"
        holder.rating.text = "4.5"

        //Glide.with(context).load(md.bannerUrl).thumbnail(0.6f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image)

        holder.itemView.setOnClickListener {
            val intent= Intent(context, CourseDetailActivity::class.java)

            Toast.makeText(context,""+md.id, Toast.LENGTH_LONG).show()

            intent.putExtra("id",md.id)
                        context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

/*    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }*/

}
