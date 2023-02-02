package com.asmanmirza.schoolpen.UI.Student.Performance.tabs

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.databinding.PerformanceConceptualFragmentBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*

class PerformanceConceptualFragmnet : Fragment() {

    private var _binding: PerformanceConceptualFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var barChart: BarChart


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PerformanceConceptualFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBarChart()
        lineChart()
        showPieChart()

    }

    fun showPieChart() {
        val data = PieData(generateData())
        binding.pieChart.data = data
        binding.pieChart.invalidate()
    }

    fun showBarChart() {
        val data = BarData(generateBarChartData())

        binding.barChart.setTouchEnabled(true)
        binding.barChart.setPinchZoom(true)

        binding.barChart.description.textSize = 10f
        binding.barChart.description.isEnabled = false
        binding.barChart.animateY(1000)
        binding.barChart.data = data

        //Hide the bottom colors
        val l: Legend = binding.barChart.legend // get legend of pie
        l.isEnabled = false

        binding.barChart.axisRight.isEnabled = false
        binding.barChart.xAxis.isEnabled = false
        binding.barChart.axisLeft.isEnabled = false


        binding.barChart.data = data
        binding.barChart.invalidate()
    }

    fun lineChart() {
        val data = LineData()

        binding.lineChart.setTouchEnabled(true)
        binding.lineChart.setPinchZoom(true)

        binding.lineChart.description.textSize = 10f
        binding.lineChart.description.isEnabled = false
        binding.lineChart.animateY(1000)
        binding.lineChart.data = data

        //Hide the bottom colors
        val l: Legend = binding.lineChart.legend // get legend of pie
        l.isEnabled = false

        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.xAxis.isEnabled = false
        binding.lineChart.axisLeft.isEnabled = false

        data.addDataSet(generateLineChartData())
        binding.lineChart.data = data
        binding.lineChart.invalidate()
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

    private fun generateBarChartData(): BarDataSet {
        val dataSet = BarDataSet(generateEntries(), "Label")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.RED
        return dataSet
    }

    private fun generateEntries(): ArrayList<BarEntry> {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 20f))
        entries.add(BarEntry(1f, 24f))
        entries.add(BarEntry(2f, 70f))
        entries.add(BarEntry(3f, 28f))
        entries.add(BarEntry(4f, 60f))
        entries.add(BarEntry(5f, 10f))
        entries.add(BarEntry(6f, 40f))
        entries.add(BarEntry(7f, 42f))
        entries.add(BarEntry(8f, 45f))
        entries.add(BarEntry(9f, 50f))
        entries.add(BarEntry(10f, 20f))
        return entries
    }

    private fun generateData(): PieDataSet {
        val dataSet = PieDataSet(generatePieEntries(), "Label")
        dataSet.colors = generateColors()
        dataSet.valueTextSize = 10f
        return dataSet
    }

    private fun generatePieEntries(): ArrayList<PieEntry> {
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(75f, ""))
        entries.add(PieEntry(78f, ""))
        entries.add(PieEntry(69f, ""))
        entries.add(PieEntry(55f, ""))
        entries.add(PieEntry(50f, ""))
        entries.add(PieEntry(24f, ""))
        return entries
    }

    private fun generateColors(): ArrayList<Int> {
        val colors = ArrayList<Int>()
        colors.add(Color.BLUE)
        colors.add(Color.RED)
        colors.add(Color.GREEN)
        colors.add(Color.YELLOW)
        colors.add(Color.MAGENTA)
        colors.add(Color.DKGRAY)
        return colors
    }
}