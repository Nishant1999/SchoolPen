package com.asmanmirza.schoolpen.UI.Teacher.Home.AssignedClasses.tabs.notestab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import java.util.*

data class NotesDataModel(
    var note: String,
    var noteTime: String
)

class NotesAdapter(val context:Context, var data : ArrayList<NotesDataModel>):
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val note: TextView = itemView.findViewById(R.id.text_notes)
        val noteDate: TextView = itemView.findViewById(R.id.text_time_stamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = data[position]
        holder.note.text = dataItem.note
        holder.noteDate.text = dataItem.noteTime
    }

    override fun getItemCount(): Int {
        return data.size
    }
}