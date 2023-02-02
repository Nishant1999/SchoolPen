package com.asmanmirza.schoolpen.UI.Student.Learn.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Learn.Models.ModelLeaderBoard
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class AdapterLeaderboardProfiles(var context: Context, var data:ArrayList<ModelLeaderBoard>) :RecyclerView.Adapter<AdapterLeaderboardProfiles.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val image:CircleImageView = itemView.findViewById(R.id.led_profile_image);
        val pos:TextView = itemView.findViewById(R.id.textPosition);
        val name:TextView = itemView.findViewById(R.id.name);
        val per:TextView = itemView.findViewById(R.id.percent);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_leaderboard_profiles, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val md = data[position]
        holder.pos.text = "${position + 2}"
        holder.name.text = md.name;
        holder.per.text = md.percent
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").thumbnail(0.5f).into(holder.image);

    }

    override fun getItemCount(): Int {

        return data.size
    }
}