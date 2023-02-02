package com.asmanmirza.schoolpen.UI.Parent.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Parent.Models.ModelFeedback

class AdapterFeeback(var context: Context, var data:ArrayList<ModelFeedback>):RecyclerView.Adapter<AdapterFeeback.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_feedback_teacher_side, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }


}