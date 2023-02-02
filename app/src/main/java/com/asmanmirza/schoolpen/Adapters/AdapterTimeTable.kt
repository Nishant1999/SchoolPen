package com.asmanmirza.schoolpen.presentation.main.Teacher.timetable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import org.w3c.dom.Text

class AdapterTimeTable(var context: Context, var data:ArrayList<ModelTimeTable>):RecyclerView.Adapter<AdapterTimeTable.ViewHolder>() {
    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val period:TextView = itemView.findViewById(R.id.tt_period);
        val className:TextView = itemView.findViewById(R.id.tt_className);
        val stream:TextView = itemView.findViewById(R.id.tt_stream);
        val duration:TextView = itemView.findViewById(R.id.tt_duration);
        val image1:ImageView = itemView.findViewById(R.id.tt_image1);
        val image2:ImageView = itemView.findViewById(R.id.tt_image2);
        val backCard:CardView = itemView.findViewById(R.id.tt_back_card);
        val layoutLunch:FrameLayout = itemView.findViewById(R.id.tt_layoutLunch);
        val live:TextView = itemView.findViewById(R.id.tt_live)


        fun isLunch(boolean: Boolean){
            if(boolean){
                period.visibility = View.INVISIBLE;
                className.visibility = View.INVISIBLE
                stream.visibility = View.INVISIBLE
                backCard.setCardBackgroundColor(Color.parseColor("#C45162"))
                image1.visibility = View.VISIBLE
                image2.visibility = View.VISIBLE
                layoutLunch.visibility = View.VISIBLE;
                duration.setTextColor(Color.parseColor("#ffffff"))
            }else{
                period.visibility = View.VISIBLE;
                className.visibility = View.VISIBLE;
                stream.visibility = View.VISIBLE;
                backCard.setCardBackgroundColor(Color.parseColor("#20C45162"))
                image1.visibility = View.GONE;
                image2.visibility = View.GONE;
                layoutLunch.visibility = View.GONE;
                duration.setTextColor(Color.parseColor("#383838"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_timetable, parent, false))
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val md = data[position]
        holder.isLunch(md.lunch)
        holder.duration.text = "${md.from} - ${md.to}"
        if(!md.lunch){
            holder.className.text = "${md.className} |"
            holder.period.text = md.period
            holder.stream.text = md.stream
        }

        if(position == 3){
            holder.live.visibility = View.VISIBLE
        }else{
            holder.live.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}