package com.asmanmirza.schoolpen.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.Models.TodayliveClassDtos
import com.asmanmirza.schoolpen.UI.Student.Home.LiveClassDetailActivity
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class AdapterHomeLiveClasses(var context: Context, var data:ArrayList<TodayliveClassDtos>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = LayoutInflater.from(container.context).inflate(
            R.layout.item_live_class1, container,
            false
        )
        setData(item, position)
        container.addView(item)
        return item
    }

    override fun getCount(): Int {
        return if(data.size > 5){
            5
        }else{
            data.size
        }
    }

    @SuppressLint("SetTextI18n")
    fun setData(itemView: View, position:Int){

        val imageDp: CircleImageView = itemView.findViewById(R.id.imageDp)
        val title: TextView = itemView.findViewById(R.id.title);
        val des: TextView = itemView.findViewById(R.id.description);
        val studentsJoined: TextView = itemView.findViewById(R.id.totalStudents);
        val md = data[position]

     //  des.text = "${md.assignedTeacher?.name} | ${md.subjectDescription}"
        title.text = md.title;
        studentsJoined.text = "0 Joined Student"//"${md.totalJoinedStudents} students joined"*/
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").thumbnail(0.5f).into(imageDp);

        itemView.setOnClickListener {
            if(position == 0){
                context.startActivity(Intent(context, LiveClassDetailActivity::class.java))
            }else{
                Toast.makeText(context, "You only can view live class", Toast.LENGTH_SHORT).show()
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