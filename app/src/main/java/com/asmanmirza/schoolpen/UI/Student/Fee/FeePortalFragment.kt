package com.asmanmirza.schoolpen.UI.Student.Fee

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Fee.Adapter.PaymentHistoryAdapter
import com.asmanmirza.schoolpen.databinding.FragmentFeePortalBinding


class FeePortalFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentFeePortalBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PaymentHistoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeePortalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tutionFeeCard.setOnClickListener(this)
        val demoData = listOf(
            DemoData("Title 1", "Description 1"),
            DemoData("Title 2", "Description 2"),
            DemoData("Title 3", "Description 3"),
            DemoData("Title 3", "Description 3"),
            DemoData("Title 3", "Description 3"),
            DemoData("Title 3", "Description 3"),
            DemoData("Title 3", "Description 3"),
        )

        val adapter = PaymentHistoryAdapter(demoData, requireContext())

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Set up the OnScrollListener for the RecyclerView
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    binding.btnMakepayment.visibility = View.VISIBLE
                    binding.floatingActionButton.hide()
                } else {
                    binding.floatingActionButton.show()
                    binding.btnMakepayment.visibility = View.GONE
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tutionFeeCard -> {
                startActivity(Intent(requireContext(), ActivityMakePayment::class.java))

            }
        }
    }

}

data class DemoData(val title: String, val description: String)
