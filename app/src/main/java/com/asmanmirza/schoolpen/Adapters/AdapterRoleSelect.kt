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
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.asmanmirza.schoolpen.R

@SuppressLint("StaticFieldLeak")
 class AdapterRoleSelect(var context:Context, var data:ArrayList<String>) :RecyclerView.Adapter<AdapterRoleSelect.ViewHolder>() {

    var selected = ""

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val role:TextView = itemView.findViewById(R.id.roleName);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_role, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.role.text = data[position]

        if(selected.isNotEmpty()){
            if(selected.toInt() == position){
                holder.role.setTextColor(Color.parseColor("#9163D7"))
                holder.role.background.clearColorFilter()
            }else{
                holder.role.setTextColor(Color.parseColor("#9163D7"))
                holder.role.background.setColorFilter(Color.parseColor("#20AAAAAA"), PorterDuff.Mode.SRC_IN)
            }
        }else{
            holder.role.setTextColor(Color.parseColor("#9163D7"))
            holder.role.background.setColorFilter(Color.parseColor("#20AAAAAA"), PorterDuff.Mode.SRC_IN)
        }
    }

    fun setSelected(i:Int){
        selected = i.toString();
    }

    override fun getItemCount(): Int {
        return data.size
    }
}