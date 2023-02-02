package com.asmanmirza.schoolpen.presentation.main.Teacher.chat

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.Models.ModelPToP
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.quarkworks.roundedframelayout.RoundedFrameLayout
import com.rishabhharit.roundedimageview.RoundedImageView

class AdapterChatPToP(var context: Context, var data: ArrayList<ModelPToP>) :
    RecyclerView.Adapter<AdapterChatPToP.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val chatMessage: TextView = itemView.findViewById(R.id.chatText);
        val chatImage: RoundedImageView = itemView.findViewById(R.id.chatImage);
        val chatBack: RoundedFrameLayout = itemView.findViewById(R.id.backFrame);
        val chatContainer: LinearLayout = itemView.findViewById(R.id.chatContainer);
        val leftLayout: LinearLayout = itemView.findViewById(R.id.layoutWeight);
        val timestamp: TextView = itemView.findViewById(R.id.textMessageSentTime)

        fun selfChat(boolean: Boolean) {
            if (boolean) {
                leftLayout.visibility = View.VISIBLE
                chatContainer.gravity = Gravity.END
                timestamp.gravity = Gravity.END
                chatBack.clippedBackgroundColor = Color.parseColor("#30C45162")
            } else {
                leftLayout.visibility = View.GONE
                chatContainer.gravity = Gravity.START
                timestamp.gravity = Gravity.START
                chatBack.clippedBackgroundColor = Color.parseColor("#109163D7")
            }
        }

        fun changeBackground(
            topLeft: Boolean,
            topRight: Boolean,
            bottomLeft: Boolean,
            bottomRight: Boolean
        ) {

            if (topLeft) {
                chatBack.cornerRadiusTopLeft = 30
            } else {
                chatBack.cornerRadiusTopLeft = 0
            }

            if (topRight) {
                chatBack.cornerRadiusTopRight = 30
            } else {
                chatBack.cornerRadiusTopRight = 0
            }

            if (bottomLeft) {
                chatBack.cornerRadiusBottomLeft = 30
            } else {
                chatBack.cornerRadiusBottomLeft = 0
            }

            if (bottomRight) {
                chatBack.cornerRadiusBottomRight = 30
            } else {
                chatBack.cornerRadiusBottomRight = 0
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val selfID = "0"
        val md = data[position];

        holder.timestamp.text = md.timeStamp
        if (md.id == selfID) {
            holder.selfChat(true)
        } else {
            holder.selfChat(false)
        }

        if(md.image.isNotEmpty()){
            holder.chatMessage.visibility = View.GONE
            holder.chatImage.visibility = View.VISIBLE
            Glide.with(context).load(md.image).thumbnail(0.9f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.chatImage);
        }else{
            holder.chatMessage.visibility = View.VISIBLE
            holder.chatImage.visibility = View.GONE
            holder.chatMessage.text = md.message;
        }

        if(position == data.size - 1 || (hasNext(position) && self(position) && !self(position + 1)) || (hasNext(position) && !self(position) && self(position + 1))){
            holder.timestamp.visibility = View.VISIBLE
        }

        if(!hasPrevious(position)){
            if(self(position)){
                holder.changeBackground(true, true, true, false)
            }else{
                holder.changeBackground(true, true, false, true)
            }
        }else{
            if (hasNext(position)){
//                val pre = getModel(position - 1)
//                val next = getModel(position + 1)
                if(self(position)) {
                    if (self(position - 1) && self(position + 1)) {
                        holder.changeBackground(true, true, true, true)
                    } else if (self(position - 1) && !self(position + 1)) {
                        holder.changeBackground(true, false, true, true)
                    } else if (!self(position - 1) || (!self(position - 1) && !self(position + 1))) {
                        holder.changeBackground(true, true, true, false)
                    }
                }else{
                    if((self(position - 1) && self(position + 1)) || (self(position - 1) && !self(position + 1))){
                        holder.changeBackground(true, true, false, true)
                    }else if(!self(position - 1) && self(position + 1)){
                        holder.changeBackground(false, true, true, true)
                    }else if(!self(position - 1) && !self(position + 1)){
                        holder.changeBackground(true, true, true, true)
                    }
                }
            }else{
                if(self(position)){
                    //val prv = getModel(position - 1);
                    if(self(position - 1)){
                        holder.changeBackground(true, false, true, true)
                    }else{
                        holder.changeBackground(true, true, true, false)
                    }
                }else{
                    if(self(position - 1)){
                        holder.changeBackground(true, true, false, true)
                    }else{
                        holder.changeBackground(false, true, true, true)
                    }
                }
            }
        }

    }

    fun self(pos:Int):Boolean{

        val selfId = "0"

        return data[pos].id == selfId;
    }

    fun hasNext( pos:Int):Boolean{
        return pos + 1 < data.size
    }

    fun hasPrevious( pos:Int):Boolean{
        return pos - 1  >= 0
    }

    fun getModel(pos:Int): ModelPToP {
        return data[pos]
    }

    override fun getItemCount(): Int {
        return data.size
    }

}