package com.asmanmirza.schoolpen.UI.Student.Performance.tabs

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.databinding.FragmentPerformanceLearningsBinding
import com.asmanmirza.schoolpen.databinding.PerformanceConceptualFragmentBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*

class PerformanceLearningsFragmnet : Fragment() {

    private var _binding: FragmentPerformanceLearningsBinding? = null
    private val binding get() = _binding!!

    private lateinit var barChart: BarChart


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerformanceLearningsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lineChart(binding.countLineChart)
        lineChart(binding.hoursLineChart)

    }

    fun lineChart(view: LineChart) {
        val data = LineData()

        view.setTouchEnabled(true)
        view.setPinchZoom(true)

        view.description.textSize = 10f
        view.description.isEnabled = false
        view.animateY(1000)
        view.data = data

        //Hide the bottom colors
        val l: Legend = view.legend // get legend of pie
        l.isEnabled = false

        view.axisRight.isEnabled = false
        view.xAxis.isEnabled = false
        view.axisLeft.isEnabled = false
        data.addDataSet(generateLineChartData())
        view.data = data
        view.invalidate()
    }

    private fun generateLineChartData(): LineDataSet {
        val dataSet = LineDataSet(generateLineEntries(), "Label")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.RED
        return dataSet
    }

    private fun generateLineEntries(): ArrayList<Entry> {
        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, 20f))
        entries.add(Entry(1f, 24f))
        entries.add(Entry(2f, 25f))
        entries.add(Entry(3f, 28f))
        entries.add(Entry(4f, 30f))
        entries.add(Entry(5f, 35f))
        entries.add(Entry(6f, 40f))
        entries.add(Entry(7f, 42f))
        entries.add(Entry(8f, 45f))
        entries.add(Entry(9f, 50f))
        entries.add(Entry(10f, 55f))
        return entries
    }
}