package com.asmanmirza.schoolpen.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.Models.ModelDates
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterDates(var context: Context, var dates:ArrayList<ModelDates>):RecyclerView.Adapter<AdapterDates.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val dateNumber:TextView = itemView.findViewById(R.id.dateNumber);
        val e:ImageButton = itemView.findViewById(R.id.dotEvent);
        val t:ImageButton = itemView.findViewById(R.id.dotTask);
        val b:ImageButton = itemView.findViewById(R.id.dotBdy);

        fun showEvents(e1:Int, e2:Int, e3:Int){

            if(e1 == 1){
                e.visibility = View.VISIBLE
            }

            if(e2 == 1){
                t.visibility = View.VISIBLE
            }

            if(e3 == 1){
                b.visibility = View.VISIBLE
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(
            R.layout.item_date,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val md = dates[position];
        if(md.dim){
            holder.dateNumber.setTextColor(Color.parseColor("#AAAAAA"))
            holder.dateNumber.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
        }else{
            if(getCurrentDate() == md.fullDate){
                holder.dateNumber.setTextColor(Color.parseColor("#ffffff"))
                holder.dateNumber.background.setColorFilter(Color.parseColor("#9163d7"), PorterDuff.Mode.SRC_IN)
            }else{
                holder.dateNumber.setTextColor(Color.parseColor("#303030"))
                holder.dateNumber.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
            }
        }
        holder.dateNumber.text = md.date.toString()

        if(md.fullDate == "19/12/2022"|| md.fullDate == "29/12/2022"){
            holder.showEvents(1, 0 ,0)
        }else if(md.fullDate == "27/12/2022" ){
            holder.showEvents(1, 1 ,1)
        }else if(md.fullDate == "23/10/2022"){
            holder.showEvents(0, 1 ,1)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.dateNumber.tooltipText = md.fullDate
        };
    }
    override fun getItemCount(): Int {
        return dates.size
    }


    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate():String{
        val format1 = SimpleDateFormat("dd/MM/yyyy")
        val dt1: Date = Date();
        return format1.format(dt1)
    }

}