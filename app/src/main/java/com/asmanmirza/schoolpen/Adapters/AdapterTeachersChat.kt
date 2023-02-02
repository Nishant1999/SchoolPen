package com.asmanmirza.schoolpen.presentation.main.Teacher.chat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.Models.ModelTeacherChat
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView

class AdapterTeachersChat(var context: Context, var data:ArrayList<ModelTeacherChat>) : RecyclerView.Adapter<AdapterTeachersChat.ViewHolder>() {
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

        val name:TextView = itemView.findViewById(R.id.textUserName);
        val title:TextView = itemView.findViewById(R.id.textUserInfo);
        val date:TextView = itemView.findViewById(R.id.textDate);
        val message:TextView = itemView.findViewById(R.id.textDescription);
        val unreadCount:TextView = itemView.findViewById(R.id.textMessageCount);
        val dp:CircleImageView = itemView.findViewById(R.id.dpImage);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teachers_chat, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val md  = data[position];
        Glide.with(context).load(md.dp).diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.5f).into(holder.dp);
        holder.title.text = md.title
        holder.date.text = md.date
        holder.message.text = md.lastMessage
        holder.name.text = "${md.name} |"

        if(md.unreadCount > 0){
            holder.unreadCount.text = md.unreadCount.toString()
            holder.date.setTextColor(Color.parseColor("#28D339"))
        }else{
            holder.unreadCount.visibility = View.GONE
            holder.date.setTextColor(Color.parseColor("#707070"))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}