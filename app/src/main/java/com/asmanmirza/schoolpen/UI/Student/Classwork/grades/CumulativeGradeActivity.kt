package com.asmanmirza.schoolpen.UI.Student.Classwork.grades

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityCumulativeGradeBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class CumulativeGradeActivity : AppCompatActivity() {

    lateinit var binding:ActivityCumulativeGradeBinding
    lateinit var pieEntry: ArrayList<PieEntry>
    lateinit var barEntriesArrayList: ArrayList<BarEntry>
   // lateinit var lineEntriesArrayList: ArrayList<Entry>
    lateinit var pieDataSet: PieDataSet
    lateinit var pieData: PieData
    lateinit var barData: BarData
    lateinit var barDataSet: BarDataSet
//    lateinit var lineDataSet: LineDataSet
    lateinit var lineDataSetList: ArrayList<ILineDataSet>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCumulativeGradeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {

            btnBack.setOnClickListener {
                finish()
            }

        }

        setPieChart()
        setBarChart()
        setLineChart()
    }

    fun setLineChart(){


        getDataSet()
        /*lineDataSet = LineDataSet(lineDataSetList)
        lineDataSet.apply {

            setAxisDependency(YAxis.AxisDependency.LEFT)
            setLineWidth(2f)
            setDrawValues(false)
            setColor(Color.CYAN)
            setCircleRadius(6f)
            setCircleHoleRadius(3f)
            setDrawCircles(false)
            setDrawHighlightIndicators(true)
            setHighlightEnabled(true)
            setHighLightColor(Color.CYAN)
            setValueTextSize(10f)
            setValueTextColor(Color.DKGRAY)
            setMode(LineDataSet.Mode.STEPPED)

        }*/
        binding.apply {

            lineChart.setTouchEnabled(true)
            lineChart.setPinchZoom(true)

            val lineData = LineData(lineDataSetList)
            lineChart.getDescription().setTextSize(10f)
            lineChart.getDescription().setEnabled(false)
            lineChart.animateY(1000)
            lineChart.setData(lineData)

            //Hide the bottom colors
            val l: Legend = binding.lineChart.getLegend() // get legend of pie
            l.isEnabled = false

            lineChart.getAxisRight().setEnabled(false)
            lineChart.xAxis.isEnabled = false
            lineChart.invalidate()
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
                ContextCompat.getColor(this@CumulativeGradeActivity, R.color.blue_chart),
                ContextCompat.getColor(this@CumulativeGradeActivity, R.color.dark_skyblue_chart),
                ContextCompat.getColor(this@CumulativeGradeActivity, R.color.yellow_chart),
                ContextCompat.getColor(this@CumulativeGradeActivity, R.color.purple_chart),
                ContextCompat.getColor(this@CumulativeGradeActivity, R.color.light_blue_chart),
                ContextCompat.getColor(this@CumulativeGradeActivity, R.color.light_green_chart)
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
        pieDataSet.setColors(
            ContextCompat.getColor(this@CumulativeGradeActivity, R.color.blue_chart),
            ContextCompat.getColor(this@CumulativeGradeActivity, R.color.dark_skyblue_chart),
            ContextCompat.getColor(this@CumulativeGradeActivity, R.color.yellow_chart),
            ContextCompat.getColor(this@CumulativeGradeActivity, R.color.purple_chart),
            ContextCompat.getColor(this@CumulativeGradeActivity, R.color.light_blue_chart),
            ContextCompat.getColor(this@CumulativeGradeActivity, R.color.light_green_chart))

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

    private fun getDataSet()  {

        lineDataSetList = ArrayList()

        val lineEntries1: ArrayList<Entry> = ArrayList()
        lineEntries1.add(Entry(10f, 5f))
        lineEntries1.add(Entry(20f, 0f))
        lineEntries1.add(Entry(30f, 3f))
        lineEntries1.add(Entry(40f, 1f))
        lineEntries1.add(Entry(50f, 4f))
        lineEntries1.add(Entry(60f, 5f))
        lineEntries1.add(Entry(70f, 6f))
        lineEntries1.add(Entry(80f, 8f))
        lineEntries1.add(Entry(90f, 10f))
        lineEntries1.add(Entry(100f, 9f))

        var lineDataSet1 =  LineDataSet(lineEntries1, "")
        lineDataSet1.setDrawCircles(true)
        lineDataSet1.setCircleRadius(4f)
        lineDataSet1.setDrawValues(false)
        lineDataSet1.setLineWidth(3f)
        lineDataSet1.setColor(ContextCompat.getColor(this@CumulativeGradeActivity, R.color.blue_chart))
        lineDataSet1.setCircleColor(ContextCompat.getColor(this@CumulativeGradeActivity, R.color.blue_chart))
        lineDataSetList.add(lineDataSet1)


        val lineEntries2: ArrayList<Entry> = ArrayList()
        lineEntries2.add(Entry(10f, 5f))
        lineEntries2.add(Entry(30f, 0f))
        lineEntries2.add(Entry(40f, 3f))
        lineEntries2.add(Entry(60f, 1f))
        lineEntries2.add(Entry(50f, 4f))
        lineEntries2.add(Entry(80f, 5f))
        lineEntries2.add(Entry(85f, 6f))
        lineEntries2.add(Entry(90f, 8f))
        lineEntries2.add(Entry(100f, 10f))
        lineEntries2.add(Entry(80f, 9f))

        var lineDataSet2 =  LineDataSet(lineEntries2, "")
        lineDataSet2.setDrawCircles(true)
        lineDataSet2.setCircleRadius(4f)
        lineDataSet2.setDrawValues(false)
        lineDataSet2.setLineWidth(3f)
        lineDataSet2.setColor(ContextCompat.getColor(this@CumulativeGradeActivity, R.color.dark_skyblue_chart))
        lineDataSet2.setCircleColor(ContextCompat.getColor(this@CumulativeGradeActivity, R.color.dark_skyblue_chart))
        lineDataSetList.add(lineDataSet2)

        val lineEntries3: ArrayList<Entry> = ArrayList()
        lineEntries3.add(Entry(20f, 5f))
        lineEntries3.add(Entry(30f, 0f))
        lineEntries3.add(Entry(50f, 3f))
        lineEntries3.add(Entry(40f, 1f))
        lineEntries3.add(Entry(70f, 4f))
        lineEntries3.add(Entry(80f, 5f))
        lineEntries3.add(Entry(85f, 6f))
        lineEntries3.add(Entry(80f, 8f))
        lineEntries3.add(Entry(90f, 10f))
        lineEntries3.add(Entry(100f, 9f))

        var lineDataSet3 =  LineDataSet(lineEntries3, "")
        lineDataSet3.setDrawCircles(true)
        lineDataSet3.setCircleRadius(4f)
        lineDataSet3.setDrawValues(false)
        lineDataSet3.setLineWidth(3f)
        lineDataSet3.setColor(ContextCompat.getColor(this@CumulativeGradeActivity, R.color.purple_chart))
        lineDataSet3.setCircleColor(ContextCompat.getColor(this@CumulativeGradeActivity, R.color.purple_chart))
        lineDataSetList.add(lineDataSet3)

    }

    class MyXAxisValueFormatter(private val mValues: Array<String>) :
        IAxisValueFormatter {
        override fun getFormattedValue(value: Float, axis: AxisBase): String {
            // "value" represents the position of the label on the axis (x or y)
            return mValues[value.toInt()]
        }

        /** this is only needed if numbers are returned, else return 0  */
        val decimalDigits: Int
            get() = 0
    }
}