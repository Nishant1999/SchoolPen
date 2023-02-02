package com.asmanmirza.schoolpen.UI.Student.assignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.asmanmirza.schoolpen.BaseActivity
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityZoomImageBinding

class ZoomImageAct : BaseActivity(),View.OnClickListener {

    lateinit var binding:ActivityZoomImageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityZoomImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            headerZoomImgAct.imgBackAssignmentHeader.setOnClickListener(this@ZoomImageAct)
            headerZoomImgAct.txtTimeAssignmentHeader.visibility = View.GONE
            headerZoomImgAct.imgBookmarkAssignmentHeader.visibility = View.GONE
            headerZoomImgAct.rvQuesNoAssignmentHeader.visibility = View.GONE
            headerZoomImgAct.txtQuestionAssignmentHeader.visibility = View.GONE

        }
    }

    override fun onClick(p0: View?) {

        when(p0){

            binding.headerZoomImgAct.imgBackAssignmentHeader -> {
                finish()
            }
        }
    }

    companion object{

        fun startActivity(context: Context){

            var intent = Intent(context, ZoomImageAct::class.java)
            context.startActivity(intent)
        }
    }
}