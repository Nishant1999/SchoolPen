package com.asmanmirza.schoolpen.UI.Student.Fee

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityMakePaymentBinding

class ActivityMakePayment : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityMakePaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMakePaymentBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding.btnBack.setOnClickListener(this)
        openFragment(FragmentMakePayment())
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

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 1) {
            onBackPressedDispatcher.onBackPressed()
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBack -> {
                val count = supportFragmentManager.backStackEntryCount

                if (count == 1) {
                    onBackPressedDispatcher.onBackPressed()
                    finish()
                } else {
                    supportFragmentManager.popBackStack()
                }
            }
        }
    }
}