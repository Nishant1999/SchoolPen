package com.asmanmirza.schoolpen.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import java.util.*
import kotlin.collections.ArrayList

class AdapterMonths(var context: Context, var months:ArrayList<String>):RecyclerView.Adapter<AdapterMonths.ViewHolder>() {

    var selected = getMonth()

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.monthName);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_month_name, parent, false))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = months[position]
        if(selected == position){
            holder.name.setTextColor(Color.parseColor("#ffffff"))
            holder.name.background.setColorFilter(Color.parseColor("#9163d7"), PorterDuff.Mode.SRC_IN)
        }else{
            holder.name.setTextColor(Color.parseColor("#707070"))
            holder.name.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
        }

    }

    override fun getItemCount(): Int {
        return months.size
    }

    fun getMonth():Int{
        val cal: Calendar = Calendar.getInstance()
        cal.time = Date()
        return cal.get(Calendar.MONTH)
    }

}