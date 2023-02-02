package com.asmanmirza.schoolpen.Adapters

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import java.text.SimpleDateFormat
import java.util.*

class AdapterWeekDays(var context: Context, var data: ArrayList<String>): RecyclerView.Adapter<AdapterWeekDays.ViewHolder>(){

    var selected = "";

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_timetable_weekday_name, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(selected.isEmpty()){
            if(data[position] == getCurrentWeekDay()){
                holder.name.background.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                holder.name.setTextColor(Color.parseColor("#C45162"))
            }else{
                holder.name.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
                holder.name.setTextColor(Color.parseColor("#ffffff"))
            }
        }else{
            if(selected.toInt() == position){
                holder.name.background.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                holder.name.setTextColor(Color.parseColor("#C45162"))
            }else{
                holder.name.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
                holder.name.setTextColor(Color.parseColor("#ffffff"))
            }
        }

        holder.name.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getCurrentWeekDay():String{
        val calendar: Calendar = Calendar.getInstance()
        val date: Date = calendar.time
        return SimpleDateFormat("EE", Locale.ENGLISH).format(date.time)
    }

}