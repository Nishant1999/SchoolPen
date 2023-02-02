package com.asmanmirza.schoolpen.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Home.PeriodActivity
import com.asmanmirza.schoolpen.Models.ModelClasses
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class AdapterHomeTodaysClasses (var context: Context, var data:ArrayList<ModelClasses>, var backDrawable:Int) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = LayoutInflater.from(container.context).inflate(
            R.layout.item_todays_class, container,
            false
        )
        setData(item, position)
        container.addView(item)
        return item
    }

    override fun getCount(): Int {
        return data.size
    }

    @SuppressLint("SetTextI18n")
    fun setData(itemView: View, position:Int){

        val backGround:FrameLayout = itemView.findViewById(R.id.backGround);
        val imageDp: CircleImageView = itemView.findViewById(R.id.imageDp)
        var period: TextView = itemView.findViewById(R.id.classPeriod);
        var stream: TextView = itemView.findViewById(R.id.classStream);
        var chapter: TextView = itemView.findViewById(R.id.classChapter);
        var teacherName: TextView = itemView.findViewById(R.id.teacherName);
        val md = data[position]

        period.text = "Period ${md.period}"
        stream.text = md.stream
        chapter.text = md.chapter
        teacherName.text = md.teacherName

        backGround.setBackgroundResource(backDrawable);
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").thumbnail(0.5f).into(imageDp);

        itemView.setOnClickListener {
            if(position == 0){
                context.startActivity(Intent(context, PeriodActivity::class.java))
            }else{
                Toast.makeText(context, "You only can view first class", Toast.LENGTH_SHORT).show()
            }
        }



    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}