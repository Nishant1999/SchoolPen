package com.asmanmirza.schoolpen.UI.Student.Learn.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Learn.Models.ModelWKnowledge
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class AdapterWorldKnowledge(var context: Context, var data:ArrayList<ModelWKnowledge>): RecyclerView.Adapter<AdapterWorldKnowledge.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.dpImage);
        val recImages: RecyclerView = itemView.findViewById(R.id.recImages);
        val name:TextView = itemView.findViewById(R.id.name);
        val postMessage:TextView = itemView.findViewById(R.id.postMessage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learn_world_knowledge, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").into(holder.image)
        holder.recImages.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        if(position == 1){
            holder.name.text = "Sonu Sharma"
            holder.postMessage.text = "The world's quietest room is located at Microsoft's headquarters in Washington state."
            holder.recImages.adapter = AdapterImages(context, ArrayList<String>().apply { add("1") });
        }else{
            holder.recImages.adapter = AdapterImages(context, ArrayList());
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}


class AdapterImages(var context: Context, var data:ArrayList<String>): RecyclerView.Adapter<AdapterImages.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val imageView:ImageView = itemView.findViewById(R.id.imageView);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_world_knowledge_image, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(!data.isEmpty()){
            Glide.with(context).load("https://bestlifeonline.com/wp-content/uploads/sites/3/2018/08/microsoft.jpg").diskCacheStrategy(
                DiskCacheStrategy.ALL).thumbnail(0.8f).into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return if(data.isEmpty()){
            3
        }else{
            1
        }
    }

}