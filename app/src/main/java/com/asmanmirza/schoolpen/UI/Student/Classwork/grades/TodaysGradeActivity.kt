package com.asmanmirza.schoolpen.UI.Student.Classwork.grades

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityTodaysGradeBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight


class TodaysGradeActivity : AppCompatActivity() {

    lateinit var binding:ActivityTodaysGradeBinding
    lateinit var pieEntry: ArrayList<PieEntry>
    lateinit var barEntriesArrayList: ArrayList<BarEntry>
    lateinit var pieDataSet: PieDataSet
    lateinit var pieData: PieData
    lateinit var barData: BarData
    lateinit var barDataSet: BarDataSet
    lateinit var xAxisLabel: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodaysGradeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {

            btnBack.setOnClickListener {
                finish()
            }

            binding.cvEnglishGradeCardAct.setOnClickListener {
                val intent = Intent(this@TodaysGradeActivity, StudentTGradeDetailActivity::class.java)
                startActivity(intent)
            }

            setPieChart()
            setBarChart()
        }
    }

    fun setBarChart(){

        binding.apply {

            /*xAxisLabel = ArrayList()
            xAxisLabel.add("English")
            xAxisLabel.add("Hindi")
            xAxisLabel.add("Science")
            xAxisLabel.add("Social Science")
            xAxisLabel.add("Math")
            xAxisLabel.add("Computer")

            barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabel)
            barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            barChart.xAxis.setLabelCount(xAxisLabel.size, false)*/

            barChart.setDrawGridBackground(false)
            barChart.xAxis.isEnabled = false
            barChart.axisLeft.isEnabled = false
            barChart.axisRight.isEnabled = false
            barChart.description.isEnabled = false

            barDataSet = BarDataSet(getBarEntries(), "")
            barDataSet.valueTextSize = 10f

            // adding color to our bar data set.
            barDataSet.setColors(
                ContextCompat.getColor(this@TodaysGradeActivity, R.color.blue_chart),
                ContextCompat.getColor(this@TodaysGradeActivity, R.color.dark_skyblue_chart),
                ContextCompat.getColor(this@TodaysGradeActivity, R.color.yellow_chart),
                ContextCompat.getColor(this@TodaysGradeActivity, R.color.purple_chart),
                ContextCompat.getColor(this@TodaysGradeActivity, R.color.light_blue_chart),
                ContextCompat.getColor(this@TodaysGradeActivity, R.color.light_green_chart)
            )

            // setting text color.
            barDataSet.setValueTextColor(Color.BLACK)

            binding.barChart.getDescription().setEnabled(false)

            //Hide the bottom colors
            val l: Legend = binding.barChart.getLegend() // get legend of pie
            l.isEnabled = false

            binding.barChart.setDrawMarkers(false) // To remove markers when click

            // passing our bar data set.
            barData = BarData(barDataSet)

            // below line is to set data to our bar chart.
            binding.barChart.setData(barData)

        }
    }

    fun setPieChart(){
        //Implement chart
        pieDataSet = PieDataSet(getBarChartData(), "")
        pieDataSet.setColors(ContextCompat.getColor(this@TodaysGradeActivity, R.color.blue_chart),
            ContextCompat.getColor(this@TodaysGradeActivity, R.color.dark_skyblue_chart),
            ContextCompat.getColor(this@TodaysGradeActivity, R.color.yellow_chart),
            ContextCompat.getColor(this@TodaysGradeActivity, R.color.purple_chart),
            ContextCompat.getColor(this@TodaysGradeActivity, R.color.light_blue_chart),
            ContextCompat.getColor(this@TodaysGradeActivity, R.color.light_green_chart))

        //Add space to chart data
        pieDataSet.sliceSpace = 2f

        //Set text color
        pieDataSet.valueTextColor = Color.WHITE

        pieDataSet.valueTextSize = 10f
        pieData = PieData(pieDataSet)
        binding.pieChart.data = pieData

        //To hide the Description lable at bottom right
        binding.pieChart.description.isEnabled = false

        //To remove labels from piece of pie
        binding.pieChart.setDrawEntryLabels(false)

        binding.pieChart.setDrawMarkers(false) // To remove markers when click

        //Hide the bottom color
        val l: Legend = binding.pieChart.getLegend() // get legend of pie
        l.isEnabled = false

        //highlight the largest value
        binding.pieChart.highlightValue(Highlight(2f, 0f, 0))
    }

    fun getBarChartData() : ArrayList<PieEntry>{
        pieEntry = ArrayList()

        pieEntry.add(PieEntry(14f, 0))
        pieEntry.add(PieEntry(75f, 1))
        pieEntry.add(PieEntry(78f, 2))
        pieEntry.add(PieEntry(69f, 3))
        pieEntry.add(PieEntry(55f, 4))
        pieEntry.add(PieEntry(50f, 5))
        return pieEntry
    }

    private fun getBarEntries(): ArrayList<BarEntry> {

        // creating a new array list
        barEntriesArrayList = ArrayList()

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(BarEntry(1f, 40f))
        barEntriesArrayList.add(BarEntry(2f, 30f))
        barEntriesArrayList.add(BarEntry(3f, 60f))
        barEntriesArrayList.add(BarEntry(4f, 50f))
        barEntriesArrayList.add(BarEntry(5f, 80f))
        barEntriesArrayList.add(BarEntry(6f, 80f))

        return  barEntriesArrayList
    }
}