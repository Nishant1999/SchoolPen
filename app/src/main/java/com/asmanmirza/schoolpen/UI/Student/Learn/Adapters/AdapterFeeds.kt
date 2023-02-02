package com.asmanmirza.schoolpen.UI.Student.Learn.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.com.stackimageview.customviews.StackImageView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Learn.Models.ModelFeeds
import com.bumptech.glide.Glide

class AdapterFeeds(var context: Context, var data:ArrayList<ModelFeeds>): RecyclerView.Adapter<AdapterFeeds.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.dpImage);
        val recOptions:RecyclerView = itemView.findViewById(R.id.recOptions);
        val stack:StackImageView = itemView.findViewById(R.id.stackImageView);


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learn_feeds, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").into(holder.image)
        holder.recOptions.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recOptions.adapter = AdapterOptions1(context, ArrayList());
        holder.stack.setImageLists(ArrayList<Any>().apply {
            add("https://api.lorem.space/image/face?w=150&h=150")
            add("https://api.lorem.space/image/face?w=151&h=151")
            add("https://api.lorem.space/image/face?w=152&h=152")
            add("https://api.lorem.space/image/face?w=153&h=153")
            add("https://api.lorem.space/image/face?w=154&h=154")
            add("https://api.lorem.space/image/face?w=155&h=155")
            add("https://api.lorem.space/image/face?w=156&h=156")
        })
    }

    override fun getItemCount(): Int {
        return 1
    }
}


class AdapterOptions1(var context: Context, var data:ArrayList<String>): RecyclerView.Adapter<AdapterOptions1.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.optionText);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_question_option, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(position == 1){
            holder.text.text = "Baba Aajam"
        }

        if(position == 0){
            holder.text.text = "Yusuf khatir"
        }

    }

    override fun getItemCount(): Int {
        return 2
    }

}