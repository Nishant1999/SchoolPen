package com.asmanmirza.schoolpen.UI.Teacher.Home.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Teacher.Home.Models.ModelAttendanceStatusDates
import com.asmanmirza.schoolpen.UI.Teacher.Home.Models.ModelStudentAttendanceStatus
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class StudentAttendenceStatusAdapter(var context:Context, var data:ArrayList<ModelStudentAttendanceStatus>):RecyclerView.Adapter<StudentAttendenceStatusAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val classTitle:TextView = itemView.findViewById(R.id.tv_class_number);
        val totalPercent:TextView = itemView.findViewById(R.id.student_attendance_percentage)
        val presentDays:TextView = itemView.findViewById(R.id.student_attendance_present);
        val absentDays:TextView = itemView.findViewById(R.id.student_attendance_absent);
        val monthSelect:Spinner = itemView.findViewById(R.id.monthSelectSpinner);
        val layoutCalender:LinearLayout = itemView.findViewById(R.id.layoutCalender);
        val btnDropDown:ImageButton = itemView.findViewById(R.id.btnOpenHideCalander)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_student_profile_attendence, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        val arrayAdapter = ArrayAdapter(context ,android.R.layout.simple_spinner_item, months);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.monthSelect.adapter = arrayAdapter;

        holder.btnDropDown.setOnClickListener {
            if(holder.btnDropDown.rotation == 0f){
                holder.btnDropDown.rotation = 180f
                holder.layoutCalender.visibility = View.VISIBLE
            }else{
                holder.layoutCalender.visibility = View.GONE
                holder.btnDropDown.rotation = 0f
            }
        }

        holder.monthSelect.onItemSelectedListener = object:OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                showCalender(context, position+1, holder.layoutCalender)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    @SuppressLint("SimpleDateFormat")
    fun showCalender(context: Context, month:Int, root:View){
        val recDates:RecyclerView = root.findViewById(R.id.recDates);
        recDates.layoutManager = GridLayoutManager(context, 7)
        val dates:ArrayList<ModelAttendanceStatusDates> = ArrayList();

        val selectedMonthDays =
            getTotalNumberOfDaysInMonth(2023, month - 1)
        var nextMonthDays = 1


        val previousMonthDays = if (month - 1 < 1) {
            getTotalNumberOfDaysInMonth(
                2023 - 1,
                11
            )
        } else {
            getTotalNumberOfDaysInMonth(
                2023,
                month - 2
            )
        }

        val c = Calendar.getInstance()
        val inputDate = "01/" + "${if(month < 10) "0$month" else "$month"}/" + "2023"
        val format1 = SimpleDateFormat("dd/MM/yyyy")
        val dt1: Date = format1.parse(inputDate) as Date
        c.time = dt1
        val firstDayPosition = if (c[Calendar.DAY_OF_WEEK] - 1 == 0) {
            7;
        } else {
            c[Calendar.DAY_OF_WEEK] - 1
        }

        for (i in 1 until 36) {
            if (firstDayPosition != 1) {
                if (i < firstDayPosition) {
                    dates.add(
                        ModelAttendanceStatusDates(
                            (previousMonthDays - (firstDayPosition - i - 1)).toString(),
                            getDate("${previousMonthDays - (firstDayPosition - i - 1)}", month - 1),
                            false, true, true
                        )
                    )
                }else {
                    if (i - (firstDayPosition - 1) <= selectedMonthDays) {
                        dates.add(
                            ModelAttendanceStatusDates(
                                (i - (firstDayPosition - 1)).toString(),
                                getDate("${i - (firstDayPosition - 1)}", month),
                                (isStudentPresent() == 1), false, false
                            )
                        )

                    }else{
                        dates.add(
                            ModelAttendanceStatusDates(nextMonthDays.toString(),
                                getDate("$nextMonthDays", month + 1),
                                false, false, true
                            )
                        )
                        nextMonthDays++;
                    }
                }

            }else{
                if (i <= selectedMonthDays) {
                    dates.add(
                        ModelAttendanceStatusDates(
                            i.toString(),
                            getDate("$i", month),
                            (isStudentPresent() == 1), false, false
                        )
                    )

                }else{
                    dates.add(
                        ModelAttendanceStatusDates(nextMonthDays.toString(),
                            getDate("$nextMonthDays", month + 1),
                            false, false,true
                        )
                    )
                    nextMonthDays++;
                }
            }
        }
        val adapterAttendanceStatusDates = AdapterStudentAttendanceDates(context, dates);
        recDates.adapter = adapterAttendanceStatusDates
    }
    fun getTotalNumberOfDaysInMonth(year: Int, month: Int): Int {
        val currentMonth = GregorianCalendar(year, month, 1)
        return currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(date:String, month:Int): String{
        return if(month - 1 < 0){
            "${if(date.toInt() < 10) "0$date" else date}/12/2023"
        }else if(month > 12){
            "${if(date.toInt() < 10) "0$date" else date}/01/2024"
        }else{
            "${if(date.toInt() < 10) "0$date" else date}/${if(month < 10) "0$month" else "$month"}/2023"
        }
    }

    fun isStudentPresent():Int{
        val present = arrayOf(1,-1,1,1,-1,1,1,1,1,1)
        val d =  present[Random().nextInt(8)]
        Log.e("d:///", "$d")
        return d
    }

}

class AdapterStudentAttendanceDates(var context: Context, var data:ArrayList<ModelAttendanceStatusDates>):RecyclerView.Adapter<AdapterStudentAttendanceDates.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val dateText:TextView = itemView.findViewById(R.id.dateNumber);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_date, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val md = data[position]
        holder.dateText.text = md.dateNumber

        if(md.dim){
            holder.dateText.setTextColor(Color.parseColor("#50707070"))
            holder.dateText.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
        }else if(isHoliday(md.fullDate)){
            holder.dateText.setTextColor(Color.parseColor("#707070"))
            holder.dateText.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
        }else if(md.present){
            holder.dateText.setTextColor(Color.parseColor("#ffffff"))
            holder.dateText.background.setColorFilter(Color.parseColor("#20A686"), PorterDuff.Mode.SRC_IN)
        }else{
            holder.dateText.setTextColor(Color.parseColor("#ffffff"))
            holder.dateText.background.setColorFilter(Color.parseColor("#D81159"), PorterDuff.Mode.SRC_IN)
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun isHoliday(fullDate:String):Boolean{
        val c = Calendar.getInstance()
        val format1 = SimpleDateFormat("dd/MM/yyyy")
        val dt1: Date = format1.parse(fullDate) as Date
        c.time = dt1
//        val f: Format = SimpleDateFormat("EEEE")
//        val str: String = f.format(dt1)
        val day = c[Calendar.DAY_OF_WEEK]
        return day == 1 || day == 7
    }

    override fun getItemCount(): Int {
        return data.size
    }
}