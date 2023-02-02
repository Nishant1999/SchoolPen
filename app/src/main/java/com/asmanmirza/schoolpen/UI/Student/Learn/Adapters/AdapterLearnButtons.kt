package com.asmanmirza.schoolpen.UI.Student.Learn.Adapters

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Learn.Models.ModelLearnButtons
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class AdapterLearnButtons(var context: Context, var data:ArrayList<ModelLearnButtons>):RecyclerView.Adapter<AdapterLearnButtons.ViewHolder>() {

    var selected = 0;

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val image:ImageView = itemView.findViewById(R.id.imageVector);
        val title:TextView = itemView.findViewById(R.id.btnTitle);
        val background:LinearLayout = itemView.findViewById(R.id.background)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learn_buttons, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position == selected){
            holder.background.background.clearColorFilter()
        }else{
            holder.background.background.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
        }
        Glide.with(context).load(data[position].vector).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image)
        holder.title.text = data[position].title

    }

    override fun getItemCount(): Int {
        return data.size
    }


}