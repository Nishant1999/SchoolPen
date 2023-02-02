package com.asmanmirza.schoolpen.UI.Student.Fee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentMakepaymentBinding

class FragmentMakePayment: Fragment(), View.OnClickListener {

    private var _binding: FragmentMakepaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMakepaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.box1.setOnClickListener (this)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.box_1 -> {
                openFragment(FragmentPaymentHistory())
            }
        }
    }
}