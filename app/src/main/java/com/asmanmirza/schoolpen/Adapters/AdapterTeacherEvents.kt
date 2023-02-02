package com.asmanmirza.schoolpen.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.Models.ModelEvents
import java.text.SimpleDateFormat
import java.util.*

class AdapterTeacherEvents(var context: Context, var data:ArrayList<ModelEvents>):RecyclerView.Adapter<AdapterTeacherEvents.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val date:TextView = itemView.findViewById(R.id.dateText)
        val month:TextView = itemView.findViewById(R.id.monthText);
        val title:TextView = itemView.findViewById(R.id.titleText);
        val duration:TextView = itemView.findViewById(R.id.durationText);
        val priority:TextView = itemView.findViewById(R.id.priorityText)
        val btnEdit:ImageButton = itemView.findViewById(R.id.btnEdit);
        val btnNotification:ImageButton = itemView.findViewById(R.id.btnNotification);

        fun tint(color:String){
            btnNotification.setColorFilter(Color.parseColor(color))
            btnNotification.background.setColorFilter(Color.parseColor("#20${color.substring(1)}"), PorterDuff.Mode.SRC_IN)
            date.setTextColor(Color.parseColor(color))
            month.setTextColor(Color.parseColor(color))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.teachers_item_calendar_events, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val md = data[position];
        if(md.type == "event"){
            holder.tint("#3D9F5B")
        }else if(md.type == "task"){
            holder.tint("#F38E20")
            holder.btnEdit.visibility = View.VISIBLE
            holder.priority.text = md.priority
            holder.priority.visibility = View.VISIBLE

            if(md.priority == "High"){
                holder.priority.background.setColorFilter(Color.parseColor("#ff4444"), PorterDuff.Mode.SRC_IN)
            }else if(md.priority == "Med"){
                holder.priority.background.setColorFilter(Color.parseColor("#FBB13C"), PorterDuff.Mode.SRC_IN)
            }else{
                holder.priority.background.setColorFilter(Color.parseColor("#AAAAAA"), PorterDuff.Mode.SRC_IN)
            }

        }else{
            holder.tint("#9163D7")
        }
        holder.title.text = md.title;
        holder.duration.text = md.duration;
        holder.month.text = getMonthName(md.month)
        holder.date.text = if(md.date < 10) "0${md.date}" else "${md.date}"
    }

    override fun getItemCount(): Int {
       return data.size
    }


    @SuppressLint("SimpleDateFormat")
    fun getMonthName(month:Int) : String{
        val parser = SimpleDateFormat("M")
        val d: Date = parser.parse(month.toString()) as Date
        val format = SimpleDateFormat("MMM")
        return format.format(d);
    }

}