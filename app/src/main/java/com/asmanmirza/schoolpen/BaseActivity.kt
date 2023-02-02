package com.asmanmirza.schoolpen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun replaceFragmentWithoutBackStack(
        fragment: Fragment,
        TAG: String?,
        bundle: Bundle?,
        fragmentResourceId: Int
    ) {
        val fragmentManager = supportFragmentManager
        val visibleFragment = fragmentManager.findFragmentByTag(TAG)
        if (visibleFragment != null && visibleFragment.isVisible) {
            return
        }
        fragment.arguments = bundle
        val fraTransaction = fragmentManager.beginTransaction()
        fraTransaction.replace(fragmentResourceId, fragment, TAG).commit()
    }
}