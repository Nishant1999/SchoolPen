package com.asmanmirza.schoolpen.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.Models.ModelDates
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class AdapterHomeDates(var context: Context, var dates: MutableList<String>, dayColor: String):RecyclerView.Adapter<AdapterHomeDates.ViewHolder>() {

    private val dayColor = dayColor
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val number:TextView = itemView.findViewById(R.id.dateNumber);
        val weekDay:TextView = itemView.findViewById(R.id.weekDay);
        val back:FrameLayout = itemView.findViewById(R.id.backGround);
        val e: ImageButton = itemView.findViewById(R.id.dotEvent);
        val t: ImageButton = itemView.findViewById(R.id.dotTask);
        val b: ImageButton = itemView.findViewById(R.id.dotBdy);

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
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_dates, parent, false))
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val md = dates[position];

        val format1 = SimpleDateFormat("dd/MM/yyyy")
        val dayOfMonth = format1.parse(md)
        val c = Calendar.getInstance()
        c.time=dayOfMonth

        val dayOfMonthText = c.get(Calendar.DAY_OF_MONTH)

        holder.number.text = dayOfMonthText.toString()


        val dt1: Date = format1.parse(md) as Date
        val dateFormat: Format = SimpleDateFormat("EEE")
        val res: String = dateFormat.format(dt1)
        holder.weekDay.text = res;

        if(getCurrentDate() == md){
            holder.back.background.setColorFilter(Color.parseColor(dayColor), PorterDuff.Mode.SRC_IN)
            holder.number.setTextColor(Color.parseColor("#ffffff"))
            holder.weekDay.setTextColor(Color.parseColor("#ffffff"))
        }else{
            holder.back.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
            holder.number.setTextColor(Color.parseColor("#383838"))
            holder.weekDay.setTextColor(Color.parseColor("#707070"))
        }

/*
        if(md.fullDate == "19/12/2022"|| md.fullDate == "29/12/2022"){
            holder.showEvents(1, 0, 0)
        }

        if(md.fullDate == "27/12/2022" || md.fullDate == "28/12/2022"){
            holder.showEvents(0, 1, 0)
        }

        if(md.fullDate == "23/12/2022" || md.fullDate == "27/12/2022"){
            holder.showEvents(0, 0, 1)
        }*/

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