package com.asmanmirza.schoolpen.UI.Student.Home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Adapters.AdapterNotice
import com.asmanmirza.schoolpen.Helpers.ApiClient
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.Models.Data
import com.asmanmirza.schoolpen.Models.ModelNotice
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import com.asmanmirza.schoolpen.databinding.ActivityNoticeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeActivity : AppCompatActivity() {

    lateinit var binding:ActivityNoticeBinding;
    lateinit var data: ArrayList<Data>
    lateinit var myApi:MyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateData()
    }

    private fun updateData(){

        binding.apply {
            myApi = ApiClient.getClient()?.create(MyApi::class.java)!!
            recNotices.layoutManager = LinearLayoutManager(this@NoticeActivity, LinearLayoutManager.VERTICAL, false)
            addData()
            btnFilter.setOnClickListener {
                showFilterPopup()
            }
            btnSortBy.setOnClickListener {
                showSortPopup()
            }
            btnBack.setOnClickListener {
                finish()
            }
            ItemClickSupport.addTo(recNotices).setOnItemClickListener { recyclerView, position, v ->
                val b = Bundle();
                b.putString("title", data[position].heading)
                b.putString("description", data[position].description)
                b.putString("file", data[position].file)
                b.putString("type", data[position].type)
                //findNavController().navigate(R.id.action_noticeFragment_to_noticeDetailFragment, b)
            }
        }
    }
      @SuppressLint("SuspiciousIndentation")
      private fun addData() {
          data=ArrayList()
          val db = TinyDB(this@NoticeActivity)

            myApi.getNotice(db.getString("schoolId").toDouble().toInt(),"Bearer"+" "+db.getString("token")).enqueue(object : Callback<ModelNotice>{
                override fun onResponse(call: Call<ModelNotice>, response: Response<ModelNotice>) {
                    if (response.isSuccessful){

                        val res: List<Data> = response.body()!!.data
                        for(i in res){
                            val d = Data(i.id,i.heading,i.description,i.date,i.file,i.type,i.school,i.classes)
                            data.add(d)
                        }
                        Log.d("++++++++data",data.toString())
                        binding.recNotices.adapter = AdapterNotice(this@NoticeActivity, data)
                    }
                    else{
                        Toast.makeText(
                            this@NoticeActivity,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ModelNotice>, t: Throwable) {
                    Toast.makeText(
                        this@NoticeActivity,
                        "Internal server error occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        //data = ArrayList();
       /* data.add(
            ModelNotice(
                "Admission Notice – 2022-23 Class-XI (Class Eleven Only)",
                "19/07/2022",
                "",
                "https://cdn.pixabay.com/photo/2020/05/31/16/53/bookmarks-5243253_960_720.jpg",
                "image"
            )
        )
        data.add(
            ModelNotice(
                "Annual college Meeting video after award ceremony",
                "10/07/2022",
                "",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                "video"
            )
        )

        data.add(
            ModelNotice(
                "Semester Exam notice",
                "10/07/2022",
                "",
                "https://www.orimi.com/pdf-test.pdf",
                "pdf"
            )
        )
        data.add(
            ModelNotice(
                "Avant garde competition announcement",
                "3/07/2022",
                "Did you know that globally nearly 1 in 4 girls ages 15–19 are not in school? These numbers (reported by UNICEF) tell a story of inequality. While a quarter of teen girls are without economic, academic, and professional pathways, only one tenth of boys face the same barriers to opportunity. Gender-based disadvantage greatly impacts transgender and nonbinary young people around the globe as well—in some places, female, trans, and nonbinary youth are ",
                "",
                "text"
            )
        )*/



    }

    private fun showFilterPopup() {
        val popup = PopupWindow(this@NoticeActivity)
        val layout: View = layoutInflater.inflate(R.layout.popup_filter_notice, null)
        popup.contentView = layout
        // Set content width and height
        // Set content width and height
        popup.height = WindowManager.LayoutParams.WRAP_CONTENT
        popup.width = WindowManager.LayoutParams.MATCH_PARENT
        popup.animationStyle = R.style.Animation


        popup.isOutsideTouchable = true
        popup.isFocusable = true
        // Show anchored to button
        // Show anchored to button
        popup.setBackgroundDrawable(BitmapDrawable())
        popup.showAtLocation(
            binding.recNotices, Gravity.BOTTOM, 0,
            0
        )
        popup.dimBehind()
    }

    private fun showSortPopup() {
        val popup = PopupWindow(this@NoticeActivity)
        val layout: View = layoutInflater.inflate(R.layout.popup_sort_by, null)
        popup.contentView = layout
        // Set content width and height
        // Set content width and height
        popup.height = WindowManager.LayoutParams.WRAP_CONTENT
        popup.width = WindowManager.LayoutParams.MATCH_PARENT
        popup.animationStyle = R.style.Animation


        popup.isOutsideTouchable = true
        popup.isFocusable = true
        // Show anchored to button
        // Show anchored to button
        popup.setBackgroundDrawable(BitmapDrawable())
        popup.showAtLocation(
            binding.recNotices, Gravity.BOTTOM, 0,
            0
        )
        popup.dimBehind()
    }

    fun PopupWindow.dimBehind() {
        val container = contentView.rootView
        val context = contentView.context
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = container.layoutParams as WindowManager.LayoutParams
        p.flags = p.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        p.dimAmount = 0.3f
        wm.updateViewLayout(container, p)
    }

}


