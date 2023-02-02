package com.asmanmirza.schoolpen.UI.Student.Learn.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView

class AdapterOptions(var context: Context, var data:ArrayList<String>): RecyclerView.Adapter<AdapterOptions.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val option: EditText = itemView.findViewById(R.id.option);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_poll_options, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val t = data[position]
        holder.option.setText(t)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}