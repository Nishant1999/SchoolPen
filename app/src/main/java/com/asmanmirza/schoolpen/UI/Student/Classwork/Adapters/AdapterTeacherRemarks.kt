package com.asmanmirza.schoolpen.UI.Student.Classwork.Adapters

import android.content.Context
import android.os.Build
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.Models.FeedBackForStudentByTeacherDTO
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class AdapterTeacherRemarks(var context: Context, var data:ArrayList<FeedBackForStudentByTeacherDTO>) : RecyclerView.Adapter<AdapterTeacherRemarks.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val dp:ImageView = itemView.findViewById(R.id.dpImage);
        val name:TextView = itemView.findViewById(R.id.name);
        val timeStamp:TextView = itemView.findViewById(R.id.timestamp);
        val subject:TextView = itemView.findViewById(R.id.subject);
        val message:TextView = itemView.findViewById(R.id.message);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teachers_remarks, parent,false))
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val md = data[position]
        holder.message.text = md.feedBackDetails

        val currentTimeMillis = System.currentTimeMillis()
        val timeInMillisFromDate=getTimeMillisFromDate(md.student.createDateTime)
        val timeDifferenceMillis = currentTimeMillis - timeInMillisFromDate
        val timeDifferenceHours = TimeUnit.MILLISECONDS.toHours(timeDifferenceMillis)
        val formattedDuration = formatDuration(timeDifferenceHours)

        holder.timeStamp.text = formattedDuration
        holder.subject.text = "Science"
        holder.name.text = md.teacher.name
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").thumbnail(0.7f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.dp)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTimeMillisFromDate(createDateTime: String): Long {


        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val dateTime = LocalDateTime.parse(createDateTime, formatter)
        val date = Date.from(dateTime.toInstant(ZoneOffset.UTC))
        val formatterString = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateString = formatterString.format(date)
        val dateFormatterString=formatterString.parse(dateString)


        val timeInMillisFromDateNumber = dateFormatterString.time

        return timeInMillisFromDateNumber
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun formatDuration(duration: Long): String {
        val days = TimeUnit.HOURS.toDays(duration)
        val hours = duration - TimeUnit.DAYS.toHours(days)
        return when {
            days >= 1 -> "$days days ago"
            else -> "$hours hours ago"
        }
    }

}