package com.asmanmirza.schoolpen.Adapters

import android.annotation.SuppressLint
import android.content.ContentProviderClient
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.Models.ModelNotice
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shockwave.pdfium.PdfDocument
import com.shockwave.pdfium.PdfiumCore


class AdapterNotice(var context: Context, var data:ArrayList<ModelNotice>) : RecyclerView.Adapter<AdapterNotice.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val title:TextView = itemView.findViewById(R.id.noticeTitle);
        val date:TextView = itemView.findViewById(R.id.noticeDate);
        val imgCard:CardView = itemView.findViewById(R.id.noticeImageCard);
        val img:ImageView = itemView.findViewById(R.id.noticeImage);
        val playBtn:ImageButton = itemView.findViewById(R.id.noticePlayBtn);
        val description:TextView = itemView.findViewById(R.id.noticeDescription);
        val showMoreBtn:TextView = itemView.findViewById(R.id.noticeShowMore);
        val downloadBtn:ImageButton = itemView.findViewById(R.id.noticeDownloadBtn);


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notice, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val md = data[position];

        holder.title.text = md.title;
        holder.date.text = md.date;

        if(md.type == "image"){
            Glide.with(context).load(md.file).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img)
        }else if(md.type == "video"){
            Glide.with(context).load(md.file).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img)
            holder.playBtn.visibility = View.VISIBLE;
        }else if(md.type == "pdf"){
            holder.showMoreBtn.visibility = View.VISIBLE
            Glide.with(context).load(R.drawable.pdf).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img)
        }else if(md.type == "text"){
            holder.description.visibility = View.VISIBLE
            holder.showMoreBtn.visibility = View.VISIBLE
            holder.imgCard.visibility = View.GONE;
            holder.description.text = md.description;
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("Recycle")
    fun getThumbnail(url:String): Bitmap? {
        val pageNumber = 0
        val pdfiumCore = PdfiumCore(context)
        try {
            val resolver = context.contentResolver
            val providerClient: ContentProviderClient? = resolver.acquireContentProviderClient(Uri.parse(url))
            val descriptor = providerClient?.openFile(Uri.parse(url), "r")
            val pdfDocument: PdfDocument = pdfiumCore.newDocument(descriptor)
            pdfiumCore.openPage(pdfDocument, pageNumber)
            val width = pdfiumCore.getPageWidthPoint(pdfDocument, pageNumber)
            val height = pdfiumCore.getPageHeightPoint(pdfDocument, pageNumber)
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            pdfiumCore.renderPageBitmap(pdfDocument, bmp, pageNumber, 0, 0, width, height)
            pdfiumCore.closeDocument(pdfDocument) // important!
            return bmp;
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}