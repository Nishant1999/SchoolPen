package com.asmanmirza.schoolpen.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.Models.ModelAttendance
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Teacher.Home.AttendanceActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class AdapterAttendance(var context: Context, var data:ArrayList<ModelAttendance>):RecyclerView.Adapter<AdapterAttendance.ViewHolder>(){
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val pos:TextView = itemView.findViewById(R.id.text_student_attendance_index);
        val name:TextView = itemView.findViewById(R.id.text_student_attendance_name);
        val image:ImageView = itemView.findViewById(R.id.homeDp1);
        val switch:SwitchCompat = itemView.findViewById(R.id.attendance_toggle_switch);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_student_attendace, parent, false))
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val md = data[position];
        holder.switch.isChecked = md.present
        holder.name.text = md.name;
        holder.pos.text = (position + 1).toString()
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);

        holder.switch.setOnCheckedChangeListener { buttonView, isChecked ->
            md.present = isChecked
            data[position] = md
            AttendanceActivity.instance.checkPresent(data)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}