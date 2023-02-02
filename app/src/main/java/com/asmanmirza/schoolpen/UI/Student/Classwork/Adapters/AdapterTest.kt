package com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.ModelTest

class AdapterTest (var context: Context,var data:ArrayList<ModelTest>) : RecyclerView.Adapter<AdapterTest.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val subject:TextView = itemView.findViewById(R.id.testSubject);
        val date:TextView = itemView.findViewById(R.id.testDate);
        val title:TextView = itemView.findViewById(R.id.testTitle);
        val image:ImageView = itemView.findViewById(R.id.testImage);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_test, parent,false))
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val md = data[position];
        if(md.upcoming){
            holder.date.text = "Upcoming ${md.date}"
        }else{
            holder.date.text = "Submit today by ${md.date}"
        }
        holder.image.setImageDrawable(context.resources.getDrawable(md.imageID))
        holder.title.text = md.title
        holder.subject.text = md.subject

    }

    override fun getItemCount(): Int {
        return data.size
    }

}