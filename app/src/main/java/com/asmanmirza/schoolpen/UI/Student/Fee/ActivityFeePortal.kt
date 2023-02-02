package com.asmanmirza.schoolpen.UI.Student.Fee

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Fee.Adapter.PaymentHistoryAdapter
import com.asmanmirza.schoolpen.databinding.ActivityFeePortalBinding

class ActivityFeePortal : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityFeePortalBinding? = null
    private val binding get() = _binding
    lateinit var adapter: PaymentHistoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFeePortalBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        openFragment(FeePortalFragment())
        binding?.btnBack?.setOnClickListener(this)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnBack -> {
                finish()
            }
        }
    }

    override fun onBackPressed() {
        finish()
        super.getOnBackPressedDispatcher().onBackPressed()
    }
}