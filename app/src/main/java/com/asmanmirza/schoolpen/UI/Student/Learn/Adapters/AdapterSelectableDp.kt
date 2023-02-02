package com.asmanmirza.schoolpen.UI.Student.Learn.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Learn.Models.ModelDp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView

class AdapterSelectableDp(var context: Context, var data:ArrayList<ModelDp>, var selected:Boolean):RecyclerView.Adapter<AdapterSelectableDp.ViewHolder>(){
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val dp:CircleImageView = itemView.findViewById(R.id.dpImage);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dp, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val md = data[position]
        Glide.with(context).load(md.imageUrl).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.dp)

    }

    override fun getItemCount(): Int {
        return if(data.size > 10){
            10
        }else{
            data.size
        }
    }


}