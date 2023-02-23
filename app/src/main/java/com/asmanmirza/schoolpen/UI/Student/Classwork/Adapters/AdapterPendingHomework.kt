package com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.icu.util.LocaleData
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.HomeWork
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class AdapterPendingHomework(var context: Context, var data:ArrayList<HomeWork>) : RecyclerView.Adapter<AdapterPendingHomework.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val title: TextView = itemView.findViewById(R.id.title)
        val subject: TextView = itemView.findViewById(R.id.subject)
        val des: TextView = itemView.findViewById(R.id.des)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pending_homework, parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val md = data[position]
        holder.subject.text=md.subject.subjectName
        holder.title.text=md.title
        holder.des.text=md.description

        holder.date.text=md.createDateTime

       /* val dateString = md.createDateTime
        val date = dateString.getDateWithServerTimeStamp()
        Log.d("LOG", "String To Date Conversion " +date.toString())
        val dateBackToString = date?.getStringTimeStampWithDate()
        Log.d("LOG", "Date To String Conversion " +dateBackToString)*/


   /*     val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a")
        val parsedDate: Date = inputFormat.parse(date) as Date
        val formattedDate: String = outputFormat.format(parsedDate)
        holder.date.text=formattedDate*/
        /*if(position == 1){
            holder.title.text = "Chemical Names"
            holder.date.text = "Due on 31/10/2022"
            holder.des.text = "Create the periodic table and write full names"

        }else{
            holder.title.text = "Digistive system"
            holder.date.text = "Due on 30/10/2022"
            holder.des.text = "Write the parts of the digestive system expalining thr functions"
            holder.subject.text=md.subject.subjectName
        }*/

    }

    override fun getItemCount(): Int {
        return 2
    }

    /*fun String.getDateWithServerTimeStamp(): Date? {
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
        try {
            return dateFormat.parse(this)
        } catch (e: ParseException) {
            return null
        }
    }
        *//** Converting from Date to String**//*
        fun Date.getStringTimeStampWithDate(): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            return dateFormat.format(this)
        }*/

}