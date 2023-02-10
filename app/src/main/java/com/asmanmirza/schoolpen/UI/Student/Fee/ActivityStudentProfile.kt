package com.asmanmirza.schoolpen.UI.Student.Fee

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.SchoolApp
import com.asmanmirza.schoolpen.UI.Student.Fee.viewModels.ViewModelProfile
import com.asmanmirza.schoolpen.UI.Student.models.MainViewModelFactory
import com.asmanmirza.schoolpen.databinding.ActvityProfileStudentBinding
import javax.inject.Inject

class ActivityStudentProfile : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActvityProfileStudentBinding? = null
    private val binding get() = _binding
    private lateinit var userDetailViewModel: ViewModelProfile

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActvityProfileStudentBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val db = TinyDB(this);

        (application as SchoolApp).applicationComponent.inject(this)

        userDetailViewModel =
            ViewModelProvider(this, mainViewModelFactory)[ViewModelProfile::class.java]
        binding?.btnDesc?.setOnClickListener(this)
        binding?.ivEditProfileStu?.setOnClickListener(this)
        binding?.backButton?.setOnClickListener(this)
        val token = db.getString("token")
        userDetailViewModel.getUserDetails(
            5, "Bearer $token"
        )

        userDetailViewModel.userData.observe(this) {
            binding?.tvUserName?.text = it.userName
            binding?.contentEmail?.text = it.email
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDesc -> {
                val fragmentManager = supportFragmentManager
                val dialogFragment = DialogFragmentInvite()
                dialogFragment.show(fragmentManager, "dialog")
            }
            R.id.ivEditProfileStu -> {
                startActivity(
                    Intent(
                        this@ActivityStudentProfile, ActivityEditProfileStudent::class.java
                    )
                )
            }
            R.id.back_button -> {
                finish()
            }
        }
    }
}